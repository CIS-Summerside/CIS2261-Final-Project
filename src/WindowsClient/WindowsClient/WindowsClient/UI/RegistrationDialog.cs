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
    public partial class RegistrationDialog : Form
    {
        public RegistrationDialog()
        {
            InitializeComponent();
        }

        private void btn_Submit_Click(object sender, EventArgs e)
        {
            string passHash = Tools.HashingTools.sha256Hash(txt_Password.Text);
            User userDetails = new User(txt_Username.Text, passHash, txt_Email.Text);
            string response = ApiUtils.Communication.postData(Api.EndpointRefs.registerURL, userDetails);
            MessageBox.Show(response);
        }
    }
}
