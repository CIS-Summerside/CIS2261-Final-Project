using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using WindowsClient.Tools;

namespace WindowsClient.UI
{
    public partial class UploadDialog : Form
    {
        Stream file;
        String name;
        long size;
        byte[] buffer;

        public UploadDialog()
        {
            InitializeComponent();
            radioButton1.Checked = true;
        }

        private void button1_Click_1(object sender, EventArgs e)
        {
            size = -1;
            DialogResult result = openFileDialog1.ShowDialog(); // Show the dialog.
            if (result == DialogResult.OK) // Test result.
            {
                file = openFileDialog1.OpenFile();
                try
                {
                    name = openFileDialog1.SafeFileName;
                    textBox1.Text = name;
                    size = file.Length;

                    buffer = new byte[size];
                    file.Read(buffer, 0, buffer.Length);

                    //not sure if necessary, but the stream might be emptied after being read the first time
                    file = openFileDialog1.OpenFile();
                }
                catch (IOException)
                {
                }
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if(Properties.Settings.Default.userToken == String.Empty)
            {
                //TODO handle this properly
                MessageBox.Show("You must be logged in to upload.");
            }
            else
            {
                String privacy = "0";
                String duration = "5";
                if (radioButton1.Checked)
                {
                    privacy = "0";
                }
                else if (radioButton2.Checked)
                {
                    privacy = "1";
                }

                duration = numericUpDown1.Value.ToString();
                UploadingDialog upDiag = new UploadingDialog();
                upDiag.Show();
                bool upload = FileTools.Upload(Api.EndpointRefs.uploadURL, privacy, duration, name, file, buffer);
                if (upload)
                {
                    this.Close();
                }
                upDiag.Close();
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
