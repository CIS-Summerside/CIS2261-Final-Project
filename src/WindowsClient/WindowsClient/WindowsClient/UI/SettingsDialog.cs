using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsClient.UI
{
    public partial class SettingsDialog : Form
    {
        public SettingsDialog()
        {
            InitializeComponent();
            string token = Properties.Settings.Default.userToken;
            string computer = Properties.Settings.Default.computerIdentifier;
            string computerHash = Properties.Settings.Default.computerIdHashed;
            txt_Token.Text = token;
            txt_Computer.Text = computer;
            txt_CmpHash.Text = computerHash;
        }

        private void btn_Ok_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
