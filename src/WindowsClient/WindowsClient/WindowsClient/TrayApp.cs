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
            ContextMenu trayMenu = new ContextMenu();

            //Add items and reference the methods called when clicked to the ContextMenu.
            trayMenu.MenuItems.Add(new MenuItem("-"));
            trayMenu.MenuItems.Add(new MenuItem("Serial Test", Serial));
            trayMenu.MenuItems.Add(new MenuItem("Login", Login));
            trayMenu.MenuItems.Add(new MenuItem("About", About));
            trayMenu.MenuItems.Add(new MenuItem(strings.Exit, Exit));

            return trayMenu;
        }

        private void Serial(object sender, EventArgs e)
        {
            Tools.ComputerTools.getSerialNumber();
        }

        private void About(object sender, EventArgs e)
        {
            UI.AboutDialog aboutWin = new UI.AboutDialog();
            aboutWin.Show();
        }

        private void Login(object sender, EventArgs e)
        {
            UI.LoginDialog loginWin = new UI.LoginDialog();
            loginWin.Show();
        }

        private void Exit(object sender, EventArgs e)
        {
            notifyIcon.Visible = false;
            Application.Exit();
        }

        private void SetTitleAndIcon(bool uploading = false)
        {
            notifyIcon.Icon = Properties.Resources.Main;
            notifyIcon.Text = "Hello Test";
        }

    }
}
