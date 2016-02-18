using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace WindowsClient.Tools
{
    static class FileTools
    {

        
        //Perform the equivalent of posting a form with a filename and two files, in HTML:
        // <form action="{url}" method="post" enctype="multipart/form-data">
        //     <input type="file" name="file" />
        // </form>
        public static System.IO.Stream Upload(string url, string filename, Stream fileStream, byte[] fileBytes)
        {
            // Convert each of the three inputs into HttpContent objects

            HttpContent stringContent = new StringContent(filename);
            // examples of converting both Stream and byte [] to HttpContent objects
            // representing input type file
            HttpContent fileStreamContent = new StreamContent(fileStream);
            HttpContent bytesContent = new ByteArrayContent(fileBytes);

            // Submit the form using HttpClient and 
            // create form data as Multipart (enctype="multipart/form-data")

            using (var client = new HttpClient())
            using (var formData = new MultipartFormDataContent())
            {
                client.DefaultRequestHeaders.Add("Authorization", "Basic Og==");
                client.DefaultRequestHeaders.Add("token", "3035e49b-20db-4776-9dce-433cd2077067");
                // Add the HttpContent objects to the form data

                 formData.Add(fileStreamContent, "file", filename);

                // Actually invoke the request to the server

                // equivalent to (action="{url}" method="post")
                var response = client.PostAsync(url, formData).Result;

                // equivalent of pressing the submit button on the form
                if (!response.IsSuccessStatusCode)
                {
                    return null;
                }
                return response.Content.ReadAsStreamAsync().Result;
            }
        }
    }
}
