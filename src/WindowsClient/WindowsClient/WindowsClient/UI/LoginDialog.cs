using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Web.Configuration;
using System.Windows.Forms;
using WindowsClient.Api;
using WindowsClient.Api.Models;
using Newtonsoft.Json.Linq;

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
            User user = new User(txt_Username.Text, passHash);
            
            bool login = Api.Endpoints.UserAuth.login(user);

            if(login) this.Close();
        }

        private void btn_Register_Click(object sender, EventArgs e)
        {
            UI.RegistrationDialog regWin = new UI.RegistrationDialog();
            regWin.Show();
        }
    }
}
