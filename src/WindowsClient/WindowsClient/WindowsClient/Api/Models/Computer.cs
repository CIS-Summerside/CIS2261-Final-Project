using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsClient.ApiData
{
    class Computer
    {
        public string identifierRaw;

        public Computer(string identifierRaw)
        {
            this.identifierRaw = identifierRaw;
        }

        public Computer()
        {
            
        }
    }
}
