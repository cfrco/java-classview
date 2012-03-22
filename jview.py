#!/usr/bin/env python

import sys,os

def main():
    args = ""
    for arg in sys.argv[1:]:
        args += arg+" "
    
    #edit this path for your env
    os.chdir("/home/cat/code/mygithub/java/classview")
    os.system("java ClassView "+args)

if __name__ == "__main__":
    main()
