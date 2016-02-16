using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using WindowsClient.Api.Models;
using Newtonsoft.Json.Linq;

namespace WindowsClient.Api.Endpoints
{
    class UserAuth
    {
        public static bool login(User user)
        {
            bool closeWin = false;
            var response = Communication.postData(Api.EndpointRefs.loginURL, user);
            JToken j = JToken.Parse(response.Item2);

            if (response.Item1 == 200)
            {
                if (j["data"].HasValues)
                {
                    Properties.Settings.Default.userToken = (string)j["data"]["token"];
                    Properties.Settings.Default.Save();
                    Properties.Settings.Default.Reload();
                    closeWin = true;
                }
                else
                {
                    MessageBox.Show((string)j["data"]);
                }
            }
            else
            {
                MessageBox.Show((string)j["data"]["error"]);
            }

            return closeWin;
        }

        public static bool register(User user)
        {
            bool closeWin = false;
            var response = Communication.postData(Api.EndpointRefs.registerURL, user);
            JToken j = JToken.Parse(response.Item2);

            try
            {
                if (response.Item1 == 201)
                {
                    MessageBox.Show((string)j["data"]["username"] + " has been registered. Please login now.");
                    closeWin = true;
                }
                else
                {
                    MessageBox.Show((string) j["data"]);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show((string) j["data"]["error"]);
            }

            return closeWin;
        }

        public static void logout()
        {
            if (Properties.Settings.Default.userToken != string.Empty)
            {
                Properties.Settings.Default.userToken = string.Empty;
                Properties.Settings.Default.Save();
                Properties.Settings.Default.Reload();
            }
        }
    }
}
