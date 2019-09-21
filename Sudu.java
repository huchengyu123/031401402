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
     * 判断在九宫格中的坐标(x,y)的位置上插入value，是否符合规则
     *
     * @param x
     * @param y
     * @param value
     * @return
     */
    public static Boolean legal(int a[][],int x, int y, int value,int m) {
 
        for (int i = 0; i < m; i++) {
            //如果列中有value，则返回false
            if (i != x && a[i][y] == value) {
                return false;
            }
            //如果行中有value，则返回false
            if (i != y && a[x][i] == value) {
                return false;
            }
        }
        if(m==9){
            //(minX,minY)是(x,y)所属小九宫格的左上角的坐标
            int minX = x / 3 * 3;
            int minY = y / 3 * 3;
     
            for (int i = minX; i < minX + 3; i++) {
                for (int j = minY; j < minY + 3; j++) {
                    //如果小九宫格中的非(x,y)的坐标上的值为value，返回false
                    if (i != x && j != y && a[i][j] == value) {
                        return false;
                    }
                }
            }
        }
        if(m==4){
            //(minX,minY)是(x,y)所属小九宫格的左上角的坐标
            int minX = x / 2 * 2;
            int minY = y / 2 * 2;

            for (int i = minX; i < minX + 2; i++) {
                for (int j = minY; j < minY + 2; j++) {
                    //如果小九宫格中的非(x,y)的坐标上的值为value，返回false
                    if (i != x && j != y && a[i][j] == value) {
                        return false;
                    }
                }
            }
        }
        if(m==8){
            //(minX,minY)是(x,y)所属小九宫格的左上角的坐标
            int minX = x / 4 * 4;
            int minY = y / 2 * 2;
     
            for (int i = minX; i < minX + 4; i++) {
                for (int j = minY; j < minY + 2; j++) {
                    //如果小九宫格中的非(x,y)的坐标上的值为value，返回false
                    if (i != x && j != y && a[i][j] == value) {
                        return false;
                    }
                }
            }
        }
        if(m==6){
            //(minX,minY)是(x,y)所属小九宫格的左上角的坐标
            int minX = x / 2 * 2;
            int minY = y / 3 * 3;
     
            for (int i = minX; i < minX + 2; i++) {
                for (int j = minY; j < minY + 3; j++) {
                    //如果小九宫格中的非(x,y)的坐标上的值为value，返回false
                    if (i != x && j != y && a[i][j] == value) {
                        return false;
                    }
                }
            }
        }
  
 
        return true;
    }

    //第三部分，是针对第二部分生成的数独游戏，使用回溯法，实现对数独的解答。
    //shuDu[][]是用来存放数独游戏的二维数组。
    //numOfSolution是用来统计shuDu[][]中存放的数独游戏的解的个数
    public static int shuDu[][] = new int[9][9];
    public static int numOfSolution=1;
    public static void setShuDu(int[][] shuDu) {
        Sudu.shuDu = shuDu;
    }
 
    /**
     * 回溯法求解数独，参考第一部分用回溯法随机生成数独的解空间的代码
     * @param k
     */
    public static void shuDu_solution(int k,int m) {
        if (k == (m*m)) {
            System.out.println("解法"+numOfSolution);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(shuDu[i][j] + " ");
                }
                System.out.println();
            }
            
             //     写入out.txt文件  
            try { 
            	
            String outfile="D:\\sudoku\\out.txt";
            File writename = new File(outfile); // 相对路径，如果没有则要建立一个新的out.txt文件  
            writename.createNewFile(); // 创建新文件  
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));  
            
            //将数组中的数据写入到文件中。每行各数据之间TAB间隔
            for(int i=0;i<m;i++){
                for(int j=0;j<m;j++){
              
                    out.write(shuDu[i][j]+" ");
                   
                }

                out.write("\r\n");
            }
            out.flush(); // 把缓存区内容压入文件  
            out.close(); // 最后记得关闭文件  
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
 
    //打印二维数组a[m][n]
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

		// 一次读一个字节
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
       

        System.out.println("读出数独文本");
        setShuDu(generateShuDu);
        displayArray(shuDu, m, m);

        System.out.println("求解");
        shuDu_solution(0,m);
    }
    
}