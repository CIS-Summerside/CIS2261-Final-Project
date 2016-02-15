using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms.VisualStyles;

namespace WindowsClient.Tools
{
    class UserTools
    {
        public static bool checkUsernameLength(string username)
        {
            return username.Length >= 4 && username.Length < 16;
        }
    }
}
