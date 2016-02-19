using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsClient.Tools
{
    static class FileTools
    {


        //Perform the equivalent of posting a form with a filename and two files, in HTML:
        // <form action="{url}" method="post" enctype="multipart/form-data">
        //     <input type="file" name="file" />
        // </form>
        public static bool Upload(string url, string privacy, string duration, string filename, Stream fileStream, byte[] fileBytes)
        {
            // Convert each of the three inputs into HttpContent objects

            HttpContent stringContent = new StringContent(filename);
            // examples of converting both Stream and byte [] to HttpContent objects
            // representing input type file
            HttpContent privacyContent = new StringContent(privacy);
            HttpContent durationContent = new StringContent(duration);

            HttpContent fileStreamContent = new StreamContent(fileStream);
            HttpContent bytesContent = new ByteArrayContent(fileBytes);

            // Submit the form using HttpClient and 
            // create form data as Multipart (enctype="multipart/form-data")

            using (var client = new HttpClient())
            using (var formData = new MultipartFormDataContent())
            {
                client.DefaultRequestHeaders.Add("Authorization", "Basic Og==");
                client.DefaultRequestHeaders.Add("token", Properties.Settings.Default.userToken);
                client.DefaultRequestHeaders.Add("cpt", Properties.Settings.Default.computerIdHashed);
                // Add the HttpContent objects to the form data

                formData.Add(privacyContent, "privacy");
                formData.Add(durationContent, "duration");
                formData.Add(fileStreamContent, "file", filename);

                // Actually invoke the request to the server

                // equivalent to (action="{url}" method="post")
                var response = client.PostAsync(url, formData).Result;
                JToken j = JToken.Parse(response.Content.ReadAsStringAsync().Result);

                switch((int)response.StatusCode)
                {
                    case 200:
                        string downloadLink = Api.EndpointRefs.downloadURL + j["data"]["downloadCode"];
                        Clipboard.SetText(downloadLink);
                        MessageBox.Show("Upload successful. The following link has been copied to your clipboard." + 
                            "\n" + downloadLink);
                        break;
                    case 401:
                        MessageBox.Show("You must log in to upload.");
                        break;
                    case 400:
                        MessageBox.Show("Everything is broken.");
                        break;
                }
                // equivalent of pressing the submit button on the form
                return response.IsSuccessStatusCode;
            }
        }
    }
}
