package test_1;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
 
public class Sudu {

   
 
    /**
     * �ж��ھŹ����е�����(x,y)��λ���ϲ���value���Ƿ���Ϲ���
     *
     * @param x
     * @param y
     * @param value
     * @return
     */
    public static Boolean legal(int a[][],int x, int y, int value,int m) {
 
        for (int i = 0; i < m; i++) {
            //���������value���򷵻�false
            if (i != x && a[i][y] == value) {
                return false;
            }
            //���������value���򷵻�false
            if (i != y && a[x][i] == value) {
                return false;
            }
        }
        if(m==9){
            //(minX,minY)��(x,y)����С�Ź�������Ͻǵ�����
            int minX = x / 3 * 3;
            int minY = y / 3 * 3;
     
            for (int i = minX; i < minX + 3; i++) {
                for (int j = minY; j < minY + 3; j++) {
                    //���С�Ź����еķ�(x,y)�������ϵ�ֵΪvalue������false
                    if (i != x && j != y && a[i][j] == value) {
                        return false;
                    }
                }
            }
        }
        if(m==4){
            //(minX,minY)��(x,y)����С�Ź�������Ͻǵ�����
            int minX = x / 2 * 2;
            int minY = y / 2 * 2;

            for (int i = minX; i < minX + 2; i++) {
                for (int j = minY; j < minY + 2; j++) {
                    //���С�Ź����еķ�(x,y)�������ϵ�ֵΪvalue������false
                    if (i != x && j != y && a[i][j] == value) {
                        return false;
                    }
                }
            }
        }
        if(m==8){
            //(minX,minY)��(x,y)����С�Ź�������Ͻǵ�����
            int minX = x / 4 * 4;
            int minY = y / 2 * 2;
     
            for (int i = minX; i < minX + 4; i++) {
                for (int j = minY; j < minY + 2; j++) {
                    //���С�Ź����еķ�(x,y)�������ϵ�ֵΪvalue������false
                    if (i != x && j != y && a[i][j] == value) {
                        return false;
                    }
                }
            }
        }
        if(m==6){
            //(minX,minY)��(x,y)����С�Ź�������Ͻǵ�����
            int minX = x / 2 * 2;
            int minY = y / 3 * 3;
     
            for (int i = minX; i < minX + 2; i++) {
                for (int j = minY; j < minY + 3; j++) {
                    //���С�Ź����еķ�(x,y)�������ϵ�ֵΪvalue������false
                    if (i != x && j != y && a[i][j] == value) {
                        return false;
                    }
                }
            }
        }
  
 
        return true;
    }

    //�������֣�����Եڶ��������ɵ�������Ϸ��ʹ�û��ݷ���ʵ�ֶ������Ľ��
    //shuDu[][]���������������Ϸ�Ķ�ά���顣
    //numOfSolution������ͳ��shuDu[][]�д�ŵ�������Ϸ�Ľ�ĸ���
    public static int shuDu[][] = new int[9][9];
    public static int numOfSolution=1;
    public static void setShuDu(int[][] shuDu) {
        Sudu.shuDu = shuDu;
    }
 
    /**
     * ���ݷ�����������ο���һ�����û��ݷ�������������Ľ�ռ�Ĵ���
     * @param k
     */
    public static void shuDu_solution(int k,int m) {
        if (k == (m*m)) {
            System.out.println("�ⷨ"+numOfSolution);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(shuDu[i][j] + " ");
                }
                System.out.println();
            }
            
             //     д��out.txt�ļ�  
            try { 
            	
            String outfile="D:\\sudoku\\out.txt";
            File writename = new File(outfile); // ���·�������û����Ҫ����һ���µ�out.txt�ļ�  
            writename.createNewFile(); // �������ļ�  
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));  
            
            //�������е�����д�뵽�ļ��С�ÿ�и�����֮��TAB���
            for(int i=0;i<m;i++){
                for(int j=0;j<m;j++){
              
                    out.write(shuDu[i][j]+" ");
                   
                }

                out.write("\r\n");
            }
            out.flush(); // �ѻ���������ѹ���ļ�  
            out.close(); // ���ǵùر��ļ�  
            }
            catch (Exception e) {  
                e.printStackTrace();  
            }  
            
            
            
 
            numOfSolution++;
            return;
        }
        int x = k / m;
        int y = k % m;
        if (shuDu[x][y] == 0) {
            for (int i = 1; i <= m; i++) {
                shuDu[x][y] = i;
                if (legal(shuDu,x, y, i,m)) {
                    shuDu_solution(k + 1,m);
                }
            }
            shuDu[x][y] = 0;
        } else {
            shuDu_solution(k + 1,m);
        }
    }
 
    //��ӡ��ά����a[m][n]
    public static void displayArray(int a[][], int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
 
    public static void main(String[] args) throws IOException {
    	
    	int m =8;
    	int generateShuDu[][]=new int[9][9];   
		String path = "D:\\sudoku";
		String filename = "input8.txt";
		/*String filename = args[0];*/
		
		File myFile = new File(path,filename);
		Reader reader = new InputStreamReader(new FileInputStream(myFile),"UTF-8"); 

		// һ�ζ�һ���ֽ�
		int tempchar;  
		int i=0;
		int j=0;
	
		    while ((tempchar = reader.read()) != -1) {  
		    if ( (((char) tempchar) != '\n') &&(((char) tempchar) != ' ')) {  
		        if(i<m){
		        	if(j<m){
		        		generateShuDu[i][j]=((char) tempchar)-48;
		        		j++;
		        	}else{	     
		        		i++;
		        		j=0;
		        		generateShuDu[i][j]=((char) tempchar)-48;
		        	}
		        	
		        }
		 /*       if(i==m){
		        	System.out.println("input.txt:\n");
		            setShuDu(generateShuDu);
		            displayArray(shuDu, m, m);

		            System.out.println("out.txt:\n");
		            shuDu_solution(0,m);
		            i=0;j=0;
		        }*/
		    }  
		}
		reader.close();
       

        System.out.println("���������ı�");
        setShuDu(generateShuDu);
        displayArray(shuDu, m, m);

        System.out.println("���");
        shuDu_solution(0,m);
    }
    
}