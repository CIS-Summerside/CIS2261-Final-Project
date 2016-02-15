using System.IO;
using System.Net;
using System.Web.Script.Serialization;
using System.Windows.Forms;

namespace WindowsClient.Api
{
    class Communication
    {
        public static string postData(string url, object data)
        {
            string result;
            var httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Method = "POST";

            try
            {
                using (var streamWriter = new StreamWriter(httpWebRequest.GetRequestStream()))
                {
                    string json = new JavaScriptSerializer().Serialize(data);
                    MessageBox.Show(json);
                    streamWriter.Write(json);
                }

                var httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
                using (var streamReader = new StreamReader(httpResponse.GetResponseStream()))
                {
                    result = streamReader.ReadToEnd();
                }
            }
            catch (WebException wex)
            {
                result = handleErrorRequests(wex);
            }

            return result;
        }

        public static string getData(string url)
        {
            string result;

            try
            {
                var httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
                var httpWebResponse = (HttpWebResponse) httpWebRequest.GetResponse();
                using (var sr = new StreamReader(httpWebResponse.GetResponseStream()))
                {
                    result = sr.ReadToEnd();
                }
            }
            catch (WebException wex)
            {
                result = handleErrorRequests(wex);
            }

            return result;
        }

        public static string handleErrorRequests(WebException wex)
        {
            string errResponse = string.Empty;
            if (wex.Response != null)
            {
                using (var errorResponse = (HttpWebResponse)wex.Response)
                {
                    using (var reader = new StreamReader(errorResponse.GetResponseStream()))
                    {
                        errResponse = reader.ReadToEnd();
                    }
                }
            }
            return errResponse;
        }
    }
}
