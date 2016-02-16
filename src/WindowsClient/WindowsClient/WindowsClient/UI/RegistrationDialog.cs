using System;
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
            bool reg = false;
            string passHash = Tools.HashingTools.sha256Hash(txt_Password.Text);
            User user = new User(txt_Username.Text, passHash, txt_Email.Text);

            if (Tools.UserTools.checkUsernameLength(user.username))
            {
                if (Tools.UserTools.checkEmailLength(user.email))
                {
                    reg = Api.Endpoints.UserAuth.register(user);
                }
                else MessageBox.Show("Email must be between 7 and 30 characters long");
            }
            else MessageBox.Show("Username must be between 4 and 16 characters long");

            if (reg) this.Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
