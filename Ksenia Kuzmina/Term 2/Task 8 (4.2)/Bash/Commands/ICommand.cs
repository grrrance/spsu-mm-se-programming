﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bash.Commands
{
	public interface ICommand
	{
		public string[] RunCommand(string[] arguments);
	}
}
