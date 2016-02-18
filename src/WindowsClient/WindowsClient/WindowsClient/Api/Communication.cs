using System;
using System.IO;
using System.Net;
using System.Net.Http.Headers;
using System.Web.Script.Serialization;
using System.Windows.Forms;

namespace WindowsClient.Api
{
    class Communication
    {
        public static Tuple<int, string> postData(string url, object data)
        {
            Tuple<int, string> jsonResult;
            var httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Method = "POST";

            try
            {
                using (var streamWriter = new StreamWriter(httpWebRequest.GetRequestStream()))
                {
                    string json = new JavaScriptSerializer().Serialize(data);
                    //MessageBox.Show(json);
                    streamWriter.Write(json);
                }

                var httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
                
                using (var streamReader = new StreamReader(httpResponse.GetResponseStream()))
                {
                    jsonResult = Tuple.Create((int) httpResponse.StatusCode, streamReader.ReadToEnd());
                }
            }
            catch (WebException wex)
            {
                jsonResult = handleErrorRequests(wex);
            }
            
            return jsonResult;
        }

        public static Tuple<int, string> getData(string url)
        {
            Tuple<int, string> jsonResult;

            try
            {
                var httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
                var httpWebResponse = (HttpWebResponse) httpWebRequest.GetResponse();
                using (var streamReader = new StreamReader(httpWebResponse.GetResponseStream()))
                {
                    jsonResult = Tuple.Create((int)httpWebResponse.StatusCode, streamReader.ReadToEnd());
                }
            }
            catch (WebException wex)
            {
                jsonResult = handleErrorRequests(wex);
            }

            return jsonResult;
        }

        public static Tuple<int, string> handleErrorRequests(WebException wex)
        {
            Tuple<int, string> jsonResult = null;
            if (wex.Response != null)
            {
                using (var errorResponse = (HttpWebResponse)wex.Response)
                {
                    using (var streamReader = new StreamReader(errorResponse.GetResponseStream()))
                    {
                        jsonResult = Tuple.Create((int)errorResponse.StatusCode, streamReader.ReadToEnd());
                    }
                }
            }
            return jsonResult;
        }
    }
}
