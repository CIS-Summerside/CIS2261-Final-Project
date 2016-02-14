namespace WindowsClient.Api.Models
{
    class User
    {
        public string username;
        public string passwordHash;
        public string email;

        public User(string username, string passwordHash, string email)
        {
            this.username = username;
            this.passwordHash = passwordHash;
            this.email = email;
        }

        public User(string username, string passwordHash)
        {
            this.username = username;
            this.passwordHash = passwordHash;
        }

        public User()
        {
            
        }
    }
}
