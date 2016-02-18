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
            FileTools.Upload(Api.EndpointRefs.uploadURL, name, file, buffer);
        }
    }
}
