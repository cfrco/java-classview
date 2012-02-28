import java.lang.reflect.*;

class ClassView
{
    public static void main(String args[])
    {
        try
        {
            Class<?> c = Class.forName(args[0]);
            String classCompleteName = args[0];
            String classSimpleName = args[0].substring(args[0].lastIndexOf(".")+1);
            String classPrefixName = args[0].substring(0,args[0].lastIndexOf(".")+1);

            String[] hiddenPrefixList = {classPrefixName,"java.lang."};
            
            System.out.println(c);
            if(args.length > 1)
            {
                if(args[1].equals("constructor"))
                    printMember(c.getConstructors(),hiddenPrefixList);
                if(args[1].equals("dconstructor"))
                    printMember(c.getDeclaredConstructors(),hiddenPrefixList);
                if(args[1].equals("method"))
                    printMember(c.getMethods(),hiddenPrefixList);
                if(args[1].equals("dmethod"))
                    printMember(c.getDeclaredMethods(),hiddenPrefixList);
                if(args[1].equals("field"))
                    printMember(c.getFields(),hiddenPrefixList);
                if(args[1].equals("dfield"))
                    printMember(c.getDeclaredFields(),hiddenPrefixList);
            }
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Unknown Class : "+args[0]);
        }
    }
    private static void printMember(Member[] mems,String[] hiddenPrefixList)
    {
        for(Member m : mems)
        {
            if(m.getDeclaringClass() == Object.class)
                continue;

            String out = m.toString();

            for(String hide : hiddenPrefixList)
                out = out.replace(hide,"");
            
            System.out.print("    ");
            System.out.println(out);
        }
    }
}
