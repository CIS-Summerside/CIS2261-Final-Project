using System;
using System.Collections.Generic;
using System.Linq;
using System.Management;
using System.Net.NetworkInformation;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsClient.Tools
{
    class ComputerTools
    {
        public static string getComputerIdentifier()
        {
            string serial = getSerialNumber();
            string os = getOsVersion();
            string mac = getMacAddress();

            return $"{os}:{serial}:{mac}";
        }

        private static string getSerialNumber()
        {
            string serial = string.Empty;

            ManagementObjectSearcher mbs = new ManagementObjectSearcher("Select * from Win32_BaseBoard");
            foreach (ManagementObject mo in mbs.Get())
            {
                serial += mo["SerialNumber"].ToString();
            }
            return serial;
        }

        private static string getOsVersion()
        {
            string version = string.Empty;

            ManagementObjectSearcher mbs = new ManagementObjectSearcher("SELECT Caption FROM Win32_OperatingSystem");
            foreach (ManagementObject os in mbs.Get())
            {
                version = os["Caption"].ToString();
                break;
            }
            return version;
        }

        private static string getMacAddress()
        {
            //TODO make this better
            NetworkInterface[] nics = NetworkInterface.GetAllNetworkInterfaces();
            String macAddress = string.Empty;
            foreach (NetworkInterface adapter in nics)
            {
                if (macAddress == String.Empty)
                {
                    macAddress = adapter.GetPhysicalAddress().ToString();
                }
            }
            return macAddress;
        }
    }
}
