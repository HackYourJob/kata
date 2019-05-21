using NFluent;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xunit;

namespace Tdd
{
    public class Test
    {
        [Fact]
        public void Test1()
        {
            Check.That("a").IsEqualTo("a");
        }
    }
}
