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
 
public class Sudoku {
	
	   /*
     * 初始化命令行参数
     */
	public static String inputFilename;
	public static String outputFilename;
	public static int m;
	public static int n;
    /**
     * 判断在九宫格中的坐标(x,y)的位置上插入value，是否符合规则
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
            //(minX,minY)是(x,y)所属小4宫格的左上角的坐标
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
            //(minX,minY)是(x,y)所属小8宫格的左上角的坐标
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
            //(minX,minY)是(x,y)所属小宫格的左上角的坐标
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
    public static int shuDu[][] = new int[9][9];
    public static void setShuDu(int[][] shuDu) {
        Sudoku.shuDu = shuDu;
    }
 
    /**
     * 回溯法求解数独，参考第一部分用回溯法随机生成数独的解空间的代码
     * @param k
     * @throws IOException 
     */
    public static void shuDu_solution(int k,int m) throws IOException {
        if (k == (m*m)) {
          String src= "D:\\sudoku\\"+outputFilename;
            try{
            FileWriter fw = new FileWriter(src,true);
            for(int i=0;i<m;i++){
                for(int j=0;j<m;j++){ 
                	fw.write(shuDu[i][j]+" ");

                }

                fw.write("\r\n");
            }
            fw.write("\r\n");
            fw.close(); // 最后记得关闭文件  
            }
            catch (Exception e) {  
                e.printStackTrace();  
            }  
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
    
    

    

    public static void loadArgs(String args[]){
    	if(args.length>0&&args!=null){
    		for(int i=0;i<args.length;i++){
    			switch (args[i]) {
				case "-i":
					inputFilename = args[++i];
					break;
				case "-o": 
					outputFilename = args[++i];
					break;
				case "-m": 
					m=Integer.valueOf(args[++i]);
					break;
				case "-n":
					n=Integer.valueOf(args[++i]);
				    break;

				default:
					break;
				}
    		}
    	}
    }

    public static void main(String[] args) throws IOException {
    	
    	loadArgs(args);
    	int generateShuDu[][]=new int[10][10];   
		File myFile = new File("D:\\sudoku",inputFilename);
		Reader reader = new InputStreamReader(new FileInputStream(myFile),"UTF-8"); 

		int tempchar;  
		int i=0;
		int j=0;
		    while ((tempchar = reader.read()) != -1) {  
		    if ( (((char) tempchar) != '\n') &&(((char) tempchar) != ' ')) {  
		        if(i<m){
		        	if(j<m){
		        		if(tempchar!=13){
		        			generateShuDu[i][j]=((char) tempchar)-48;
			        		j++;
		        		}
		        	}else{	
		        		i++;
		        		j=0;
		        		generateShuDu[i][j]=((char) tempchar)-48;
		        	}
		        }
		        if(i==m){
		        	if(n!=0){
			            setShuDu(generateShuDu);		            
			            shuDu_solution(0,m);
			            n--;
			            i=0;j=0;
		        	}
		        	
		        }
		    }  
		}
		reader.close();
        
    }
    
}