using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using WindowsClient.ApiData;
using WindowsClient.Tools;
using Newtonsoft.Json.Linq;

namespace WindowsClient.Api.Endpoints
{
    class ComputerAdder
    {
        public static void addComputer()
        {
            Computer computer = new Computer();
            computer.identifierRaw = ComputerTools.getComputerIdentifier();
            var response = Communication.postData(Api.EndpointRefs.computerUrl, computer);

            switch (response.Item1)
            {
                case 201:
                case 302:
                    Properties.Settings.Default.computerIdentifier = computer.identifierRaw;
                    Properties.Settings.Default.computerIdHashed = Tools.HashingTools.sha256Hash(computer.identifierRaw);
                    break;
                default:
                    Properties.Settings.Default.computerIdentifier = string.Empty;
                    Properties.Settings.Default.computerIdHashed = string.Empty;
                    break;
            }

            Properties.Settings.Default.Save();
            Properties.Settings.Default.Reload();
        }
    }
}
