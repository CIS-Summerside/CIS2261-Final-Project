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
            
            switch(response.Item1){
                case 200:
                    closeWin = processToken(j);
                    break;
                case 302:
                case 401:
                case 404:
                    MessageBox.Show((string)j["data"]);
                    break;
                case 400:
                    MessageBox.Show((string) j["data"]["error"]);
                    break;
                default:
                    MessageBox.Show("Failed logging In");
                    break;
            }

            return closeWin;
        }

        public static bool register(User user)
        {
            bool closeWin = false;
            var response = Communication.postData(Api.EndpointRefs.registerURL, user);
            JToken j = JToken.Parse(response.Item2);

            switch (response.Item1)
            {
                case 201:
                    MessageBox.Show((string)j["data"]["username"] + " has been registered. Please login now.");
                    closeWin = true;
                    break;
                case 302:
                    MessageBox.Show("User by that name already exists.");
                    break;
                case 400:
                    MessageBox.Show((string)j["data"]["error"]);
                    break;
                default:
                    MessageBox.Show("Failed to register user.");
                    break;
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

        public static bool processToken(JToken j)
        {
            if (j["data"].HasValues)
            {
                Properties.Settings.Default.userToken = (string) j["data"]["token"];
                Properties.Settings.Default.Save();
                Properties.Settings.Default.Reload();
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
