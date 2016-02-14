using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Web.Script.Serialization;
using System.Windows.Forms;

namespace WindowsClient.ApiUtils
{
    class Communication
    {
        public static string postData(string url, object data)
        {
            string result;
            var httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Method = "POST";

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
            return result;
        }

        public static string getData(string url)
        {
            string result;
            var httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            var httpWebResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (var sr = new StreamReader(httpWebResponse.GetResponseStream()))
            {
                result = sr.ReadToEnd();
            }
            return result;
        }
    }
}
