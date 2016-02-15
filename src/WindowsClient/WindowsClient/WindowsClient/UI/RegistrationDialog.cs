using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using WindowsClient.Api;
using WindowsClient.Api.Models;
using Newtonsoft.Json.Linq;

namespace WindowsClient.UI
{
    public partial class RegistrationDialog : Form
    {
        public RegistrationDialog()
        {
            InitializeComponent();
        }

        private void btn_Submit_Click(object sender, EventArgs e)
        {
            string passHash = Tools.HashingTools.sha256Hash(txt_Password.Text);
            User user = new User(txt_Username.Text, passHash, txt_Email.Text);
            
            bool reg = Api.Endpoints.UserAuth.register(user);

            if(reg) this.Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
