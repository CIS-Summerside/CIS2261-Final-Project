using System;
using System.Collections.Generic;
using System.Linq;
using System.Management;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsClient.Tools
{
    class ComputerTools
    {
        public static string getSerialNumber()
        {
            string serial = string.Empty;

            ManagementObjectSearcher mbs = new ManagementObjectSearcher("Select * from Win32_BaseBoard");
            foreach (ManagementObject mo in mbs.Get())
            {
                serial += mo["SerialNumber"].ToString();
            }
            return serial;
        }

        public static string getMacAddress()
        {
            return string.Empty;
        }
    }
}
