//设置按钮监听方法ButttonLitener类
import java.awt.event.ActionListener;
import javax.swing.*; 
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JComboBox;
//实现对JPanel的监听接口处理
public class ButtonListener implements GoBangconfig,ActionListener{
	public GoBangframe gf;
	public JComboBox board;
	public static int firstcheck;
	public ButtonListener(GoBangframe gf,JComboBox board) {
		this.gf=gf;//获取左半部分的画板
		this.board=board;//获取可选框对象
	}
	//当界面发生操作时进行处理
	public void actionPerformed(ActionEvent e) {

		//必须得用else if，因为如果没有else if你每次在右边的界面点击时它都会获取人人对战或者人机对战的信息，每次都会重置棋盘数组
		//获取当前被点击按钮的内容，判断是不是"开始新游戏"这个按钮
		if(e.getActionCommand().equals("开始新游戏")) {
			//用于选择模式
			String str = JOptionPane.showInputDialog("请选择游戏模式（输入0为人人对战，输入1为人机对战）:");
			for(int i =0;i <= 0;i++) {
		        gf.ChooseType = Integer.parseInt(str);
	            if(gf.ChooseType == 0){
	                JOptionPane.showMessageDialog(null,"模式更改为：人人对战");
	            }
	            else if(gf.ChooseType == 1){
	                JOptionPane.showMessageDialog(null,"模式更改为：人机对战");
	            }
	            else {
	                JOptionPane.showMessageDialog(null,"输入错误,重新输入");
	            }
			}
			
			firstcheck =0;
			//重绘棋盘
		    for(int i=0;i<gf.isAvail.length;i++)
			   for(int j=0;j<gf.isAvail[i].length;j++)
			    	 gf.isAvail[i][j]=0;
		    gf.repaint();
			//如果是开始新游戏的按钮，再为左半部分设置监听方法
			gf.turn=1;
		}
		//判断当前点击的按钮是不是悔棋
		else if(e.getActionCommand().equals("悔棋")) {
			if((gf.ChessPositonList.size()>1)&&(gf.turn!=0)) {
				//把棋子数组相应的位置置为0；
				ChessPosition l=new ChessPosition();
				//获取最后一个棋子的对象信息
				l=gf.ChessPositonList.remove(gf.ChessPositonList.size()-1);
				//把相应的数组位置置为0
				gf.isAvail[l.Listi][l.Listj]=0;
				//把玩家还原为上一步的玩家
				if(gf.turn==1) gf.turn++;
				else gf.turn--;
				
				//直接调用gf的重绘方法，重绘方法的画笔应该是在棋盘页面还没生成的时候就要获取
				//调用repaint会自动调用paint方法，而且不用给参数
				gf.repaint();
				//gf.paint(gf.getGraphics());

			}
			else {
				gf.PopUp("错误提示","不能悔棋!");
			}
		}
		else if(e.getActionCommand().equals("认输")) {
			if(gf.turn==1) gf.PopUp("游戏结果","白方赢");
			else gf.PopUp("游戏结果","黑方赢");
		    //重新把棋盘设置为不可操作
		    gf.turn=0;
		}
		else if(board.getSelectedItem().equals("原色")) {
			 gf.boardkind=0;
			 gf.turn=0;
		}
		else if(board.getSelectedItem().equals("泼墨边")) {
			 gf.boardkind=1;
			 gf.turn=0;
		}
		else if(board.getSelectedItem().equals("油彩边")) {
			 gf.boardkind=2;
			 gf.turn=0;
		}
		else if(board.getSelectedItem().equals("白夹黑")) {
			 gf.boardkind=3;
			 gf.turn=0;
		}
		else if(board.getSelectedItem().equals("探照灯")) {
			 gf.boardkind=4;
			 gf.turn=0;
		}
		else if(board.getSelectedItem().equals("金色")) {
			 gf.boardkind=5;
			 gf.turn=0;
		}
		else if(board.getSelectedItem().equals("枯灰")) {
			 gf.boardkind=6;
			 gf.turn=0;
		}
		else if(board.getSelectedItem().equals("大理石")) {
			 gf.boardkind=7;
			 gf.turn=0;
		}
	}
	
}
