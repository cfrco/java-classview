#!/usr/bin/env python

import sys,os

def main():
    args = ""
    for arg in sys.argv[1:]:
        args += arg+" "
    
    os.chdir("/home/cat/code/mygithub/java/classview")
    os.system("java ClassView "+args)

if __name__ == "__main__":
    main()
