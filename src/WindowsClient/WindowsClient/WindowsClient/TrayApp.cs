using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using WindowsClient.Translations;

namespace WindowsClient
{
    class TrayApp : ApplicationContext
    {
        private NotifyIcon notifyIcon = new NotifyIcon();

        public TrayApp()
        {
            //Set the default icon of the TrayApp, set the ContextMenu for the app ad the menu built above, set icon to visible.
            SetTitleAndIcon();
            notifyIcon.ContextMenu = CreateMenu();
            notifyIcon.Visible = true;
        }

        private ContextMenu CreateMenu()
        {
            ContextMenu mainTrayMenu = new ContextMenu();

            //Add items and reference the methods called when clicked to the ContextMenu.
            mainTrayMenu.MenuItems.Add(new MenuItem("Upload File", Upload));
            mainTrayMenu.MenuItems.Add(new MenuItem("-"));
            mainTrayMenu.MenuItems.Add(new MenuItem("Login/Logout", Login));
            mainTrayMenu.MenuItems.Add(new MenuItem("About", About));
            mainTrayMenu.MenuItems.Add(new MenuItem(strings.Exit, Exit));

            return mainTrayMenu;
        }

        private void Upload(object sender, EventArgs e)
        {
            
        }

        private void Login(object sender, EventArgs e)
        {
            UI.LoginDialog loginWin = new UI.LoginDialog();
            loginWin.Show();
        }

        private void Logout(object sender, EventArgs e)
        {
            UI.LoginDialog loginWin = new UI.LoginDialog();
            loginWin.Show();
        }

        private void About(object sender, EventArgs e)
        {
            UI.AboutDialog aboutWin = new UI.AboutDialog();
            aboutWin.Show();
        }

        private void Exit(object sender, EventArgs e)
        {
            notifyIcon.Visible = false;
            Application.Exit();
        }

        private void SetTitleAndIcon(bool uploading = false)
        {
            notifyIcon.Icon = Properties.Resources.Main;
            notifyIcon.Text = "TFHS";
        }

    }
}
