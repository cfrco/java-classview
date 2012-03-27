import java.lang.reflect.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

class ClassView
{
    public static void main(String args[])
    {
        try
        {
            String[] DefaultPackage = {
                "java.lang.",
                "java.util."
            };
            Class<?> c = Class.forName("java.lang.Object");
            int i = 0;
            while(i<DefaultPackage.length)
            {
                try
                {
                    c = Class.forName(DefaultPackage[i]+args[0]);
                    break;
                }
                catch (ClassNotFoundException e)
                { ++i; }
            }

            if(i>=DefaultPackage.length)
                c = Class.forName(args[0]);

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
            else
            {
                printAll(c,hiddenPrefixList);
            }
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Unknown Class : "+args[0]);
        }
    }
    private static void printAll(Class<?> c,String[] hiddenPrefixList)
    {
        List<String> list = new ArrayList<String>();

        list.addAll(getMember(c.getConstructors(),hiddenPrefixList));
        list.addAll(getMember(c.getDeclaredConstructors(),hiddenPrefixList));
        list.addAll(getMember(c.getMethods(),hiddenPrefixList));
        list.addAll(getMember(c.getDeclaredMethods(),hiddenPrefixList));
        list.addAll(getMember(c.getFields(),hiddenPrefixList));
        list.addAll(getMember(c.getDeclaredFields(),hiddenPrefixList));

        Object[] arr = list.toArray();

        Set<String> set = new HashSet<String>();

        for(Object e : arr)
            set.add((String)e);
        
        arr = set.toArray();
        String[] sarr = new String[arr.length];

        for(int i=0;i<arr.length;++i)
            sarr[i] = (String)arr[i];

        Arrays.sort(sarr);

        for(int i=0;i<arr.length;++i)
        {
            System.out.print("    ");
            System.out.println(sarr[i]);
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
    private static List<String> getMember(Member[] mems,String[] hiddenPrefixList)
    {

        List<String> list = new ArrayList<String>();
        for(Member m : mems)
        {
            if(m.getDeclaringClass() == Object.class)
                continue;

            String out = m.toString();

            for(String hide : hiddenPrefixList)
                out = out.replace(hide,"");
            list.add(out); 
        }
        return list;
    }
}
