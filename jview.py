#!/usr/bin/env python

import sys,os

def main():
    args = ""
    for arg in sys.argv[1:]:
        args += arg+" "
    
    #edit this path for your env
    path = "/home/cat/code/mygithub/java/classview:./"
    os.system("java -cp "+path+" ClassView "+args)

if __name__ == "__main__":
    main()
