using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using WindowsClient.Api.Models;

namespace WindowsClient.UI
{
    public partial class LoginDialog : Form
    {
        public LoginDialog()
        {
            InitializeComponent();
        }

        private void btn_Login_Click(object sender, EventArgs e)
        {
            string passHash = Tools.HashingTools.sha256Hash(txt_Password.Text);
            User userDetails = new User(txt_Username.Text, passHash);
            string response = ApiUtils.Communication.postData(Api.EndpointRefs.loginURL, userDetails);
            MessageBox.Show(response);
        }

        private void btn_Register_Click(object sender, EventArgs e)
        {
            UI.RegistrationDialog regWin = new UI.RegistrationDialog();
            regWin.Show();
        }
    }
}
