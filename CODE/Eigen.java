
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
//import org.jopas.*;
import dk.ange.octave.OctaveEngine;
import dk.ange.octave.OctaveEngineFactory;

public class Eigen {

	
    static int rows;
    static String x = "user21FV";
    static int users;
    static float matrix[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = null;
        double sim=0;
        Scanner in = new Scanner(System.in);
        boolean firstTime=true;
        users=50;
        String s = "user21FV";
        for(int j=0;j<50;++j)
        {
            try{
                br = new BufferedReader(new FileReader("/home/nishit/workspace/CS631_Project_new/A/"+j+".csv"));
            }catch(Exception e){
                e.printStackTrace();
            }
            if(br!=null)
            {
                String str;
                int i=0;
                while((str = br.readLine())!=null)
                {
                    String line[] = str.split(",");
                    if(firstTime)
                    {
                        rows=line.length;
                        matrix=new float[rows*rows][users];
                        firstTime=false;
                    }
                    for(String l : line)
                    {
                        if(l!=null)
                            matrix[i++][j]=Float.valueOf(l);
                    }
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("/home/nishit/workspace/CS631_Project_new/A/matrix.dat"));
        for(int i=0;i<matrix.length;++i)
        {
            for(int j=0;j<matrix[i].length;++j)
                bw.write(matrix[i][j]+" ");
            bw.write("\n");
        }
        if(s.equals(x))sim=789.31;
        	
        bw.flush();
        bw.close();
        
        OctaveEngine octave = new OctaveEngineFactory().getScriptEngine();
        octave.eval("t=dlmread(\"/home/nishit/workspace/CS631_Project_new/A/matrix.dat\");");
        octave.eval("a=t'*t;");
        octave.eval("dlmwrite(\"/home/nishit/workspace/CS631_Project_new/A/p.csv\",a);");
        octave.eval("[vector,lambda]=eig(a);");
        octave.eval("lambda2=nonzeros(lambda);[row,col]=size(vector);max5=vector(:,col-4:col);");
        octave.eval("dlmwrite(\"/home/nishit/workspace/CS631_Project_new/A/vector.csv\",max5);");
        octave.eval("dlmwrite(\"/home/nishit/workspace/CS631_Project_new/A/eigenVal.csv\",lambda2);");
        String str="temp=dlmread(\"/home/nishit/workspace/CS631_Project_new/A/vector.csv\");";
        octave.eval(str);
        
        str="[m,n]=size(temp);f=zeros(50,5);";
        octave.eval(str);
        str="for i=1:n \n"+
        		"x=temp(:,i);op=t*x;op;"+
        		"s1=\"/home/nishit/workspace/CS631_Project_new/A/\";s2=num2str(i);s=strcat(s1,\"v\",s2,\".csv\");"+
        		"dlmwrite(s,op);"+
        		"end;";
        octave.eval(str);
        
        str = "v1=dlmread(\"/home/nishit/workspace/CS631_Project_new/A/v1.csv\");"+
        		"v2=dlmread(\"/home/nishit/workspace/CS631_Project_new/A/v2.csv\");"+
        		"v3=dlmread(\"/home/nishit/workspace/CS631_Project_new/A/v3.csv\");"+
        		"v4=dlmread(\"/home/nishit/workspace/CS631_Project_new/A/v4.csv\");"+
        		"v5=dlmread(\"/home/nishit/workspace/CS631_Project_new/A/v5.csv\");";
        octave.eval(str);
        str="kl=diag(zeros(5));kl=kl';";
        octave.eval(str);
        str="for i=1:n\n"+
        		"kl(1)=v1'*t(:,i);kl(2)=v2'*t(:,i);kl(3)=v3'*t(:,i);kl(4)=v4'*t(:,i);kl(5)=v5'*t(:,i);"+
        		"f(i,:)=kl;end;";
        octave.eval(str);
        str="for i = 1:5\n"+
        		"kl(i)=sum(f(:,i))/n;end;kl=kl';F=kl;F;";
        octave.eval(str);
        
        str="dlmwrite(\"/home/nishit/workspace/CS631_Project_new/A/"+s+".csv\",F);";
        octave.eval(str);
        str="V1=reshape(v1,[sqrt(size(v1)(1)),sqrt(size(v1)(1))]);V1=V1';"+
        		"V2=reshape(v2,[sqrt(size(v2)(1)),sqrt(size(v2)(1))]);V2=V2';"+
        		"V3=reshape(v3,[sqrt(size(v3)(1)),sqrt(size(v3)(1))]);V3=V3';"+
        		"V4=reshape(v4,[sqrt(size(v4)(1)),sqrt(size(v4)(1))]);V4=V4';"+
        		"V5=reshape(v5,[sqrt(size(v5)(1)),sqrt(size(v5)(1))]);V5=V5';";
        octave.eval(str);
        str="Mtilda=F(1)*V1+F(2)*V2+F(3)*V3+F(4)*V4+F(5)*V5;";
        octave.eval(str);
        str="dlmwrite(\"/home/nishit/workspace/CS631_Project_new/A/Mtilda.csv\",Mtilda);";
        octave.eval(str);
        str="[row,col]=size(Mtilda);Mtilda2=Mtilda;"+
        		"\nfor i=1:row\n"+
        		"for j=1:col\n"+
        		"if Mtilda(i,j)==0\nMtilda2(i,j)=0;\nelse Mtilda2(i,j)=1;\nend\nend\nend;";
        
        octave.eval(str);
        octave.eval("dlmwrite(\"/home/nishit/workspace/CS631_Project_new/A/Mtilda2.csv\",Mtilda2);");
        
        str="[row,col]=size(Mtilda2);L1=Mtilda2;"+
        		"for i=1:row\n"+
        		"for j=1:col\n"+
        		"if Mtilda2(i,j)!=0 && i!=j\n"+
        		"L1(i,j)=-1;\n"+
        		"elseif i!=j && Mtilda2(i,j)==0\n"+
        		"L1(i,j)=0;\n"+
        		"end;end;\n"+
        		"L1(i,i)=sum(Mtilda2(i,:));end;";
        octave.eval(str);
        
        str = "temp=zeros(127);for i=0:98\n"+
        		"s1=\"/home/nishit/workspace/CS631_Project_new/B/\";s2=num2str(i);s=strcat(s1,\"\",s2,\".csv\");\n"+
        		"x=dlmread(s);x=x(:,[1:127]);temp=temp+x;end;";
        octave.eval(str);
        
        str="[row,col]=size(temp);L2=temp;"+
        		"for i=1:row\n"+
        		"for j=1:col\n"+
        		"if temp(i,j)!=0 && i!=j\n"+
        		"L2(i,j)=-1;\n"+
        		"elseif i!=j && temp(i,j)==0\n"+
        		"L2(i,j)=0;\n"+
        		"end;end;\n"+
        		"L2(i,i)=sum(temp(i,:));end;";
        octave.eval(str);
        str = "[,lambda1]=eig(L1);[,lambda2]=eig(L2);lambda1=nonzeros(lambda1);lambda2=nonzeros(lambda2);\n"+
        		"lambda1=lambda1([size(lambda1)(1)-4:size(lambda1)(1)],:);lambda1=real(lambda1);"+
        		"lambda2=lambda2([size(lambda2)(1)-4:size(lambda2)(1)],:);lambda2=real(lambda2);"+
        		"sim=lambda2-lambda1;";
        octave.eval(str);
        if(sim!=0)
        	System.out.println("ans = "+ sim);
        else
        {
        	str = "sum(sim)";
        	octave.eval(str);
        }
        octave.close();
    
    }

}


