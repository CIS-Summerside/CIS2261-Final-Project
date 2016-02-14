using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Security.Cryptography;

namespace WindowsClient.Tools
{
    class HashingTools
    {

        public static string sha256Hash(string strToHash)
        {
            SHA256Managed crypt = new SHA256Managed();
            string hash = String.Empty;
            byte[] crypto = crypt.ComputeHash(Encoding.ASCII.GetBytes(strToHash), 0, Encoding.ASCII.GetByteCount(strToHash));
            return crypto.Aggregate(hash, (current, theByte) => current + theByte.ToString("x2"));
        }
    }
}
