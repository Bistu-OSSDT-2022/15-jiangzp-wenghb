//���ð�ť��������ButttonLitener��
import java.awt.event.ActionListener;
import javax.swing.*; 
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JComboBox;
//ʵ�ֶ�JPanel�ļ����ӿڴ���
public class ButtonListener implements GoBangconfig,ActionListener{
	public GoBangframe gf;
	public JComboBox board;
	public static int firstcheck;
	public ButtonListener(GoBangframe gf,JComboBox board) {
		this.gf=gf;//��ȡ��벿�ֵĻ���
		this.board=board;//��ȡ��ѡ�����
	}
	//�����淢������ʱ���д���
	public void actionPerformed(ActionEvent e) {

		//�������else if����Ϊ���û��else if��ÿ�����ұߵĽ�����ʱ�������ȡ���˶�ս�����˻���ս����Ϣ��ÿ�ζ���������������
		//��ȡ��ǰ�������ť�����ݣ��ж��ǲ���"��ʼ����Ϸ"�����ť
		if(e.getActionCommand().equals("��ʼ����Ϸ")) {
			//����ѡ��ģʽ
			String str = JOptionPane.showInputDialog("��ѡ����Ϸģʽ������0Ϊ���˶�ս������1Ϊ�˻���ս��:");
			for(int i =0;i <= 0;i++) {
		        gf.ChooseType = Integer.parseInt(str);
	            if(gf.ChooseType == 0){
	                JOptionPane.showMessageDialog(null,"ģʽ����Ϊ�����˶�ս");
	            }
	            else if(gf.ChooseType == 1){
	                JOptionPane.showMessageDialog(null,"ģʽ����Ϊ���˻���ս");
	            }
	            else {
	                JOptionPane.showMessageDialog(null,"�������,��������");
	            }
			}
			
			firstcheck =0;
			//�ػ�����
		    for(int i=0;i<gf.isAvail.length;i++)
			   for(int j=0;j<gf.isAvail[i].length;j++)
			    	 gf.isAvail[i][j]=0;
		    gf.repaint();
			//����ǿ�ʼ����Ϸ�İ�ť����Ϊ��벿�����ü�������
			gf.turn=1;
		}
		//�жϵ�ǰ����İ�ť�ǲ��ǻ���
		else if(e.getActionCommand().equals("����")) {
			if((gf.ChessPositonList.size()>1)&&(gf.turn!=0)) {
				//������������Ӧ��λ����Ϊ0��
				ChessPosition l=new ChessPosition();
				//��ȡ���һ�����ӵĶ�����Ϣ
				l=gf.ChessPositonList.remove(gf.ChessPositonList.size()-1);
				//����Ӧ������λ����Ϊ0
				gf.isAvail[l.Listi][l.Listj]=0;
				//����һ�ԭΪ��һ�������
				if(gf.turn==1) gf.turn++;
				else gf.turn--;
				
				//ֱ�ӵ���gf���ػ淽�����ػ淽���Ļ���Ӧ����������ҳ�滹û���ɵ�ʱ���Ҫ��ȡ
				//����repaint���Զ�����paint���������Ҳ��ø�����
				gf.repaint();
				//gf.paint(gf.getGraphics());

			}
			else {
				gf.PopUp("������ʾ","���ܻ���!");
			}
		}
		else if(e.getActionCommand().equals("����")) {
			if(gf.turn==1) gf.PopUp("��Ϸ���","�׷�Ӯ");
			else gf.PopUp("��Ϸ���","�ڷ�Ӯ");
		    //���°���������Ϊ���ɲ���
		    gf.turn=0;
		}
		else if(board.getSelectedItem().equals("ԭɫ")) {
			 gf.boardkind=0;
			 gf.turn=0;
		}
		else if(board.getSelectedItem().equals("��ī��")) {
			 gf.boardkind=1;
			 gf.turn=0;
		}
		else if(board.getSelectedItem().equals("�Ͳʱ�")) {
			 gf.boardkind=2;
			 gf.turn=0;
		}
		else if(board.getSelectedItem().equals("�׼к�")) {
			 gf.boardkind=3;
			 gf.turn=0;
		}
		else if(board.getSelectedItem().equals("̽�յ�")) {
			 gf.boardkind=4;
			 gf.turn=0;
		}
		else if(board.getSelectedItem().equals("��ɫ")) {
			 gf.boardkind=5;
			 gf.turn=0;
		}
		else if(board.getSelectedItem().equals("�ݻ�")) {
			 gf.boardkind=6;
			 gf.turn=0;
		}
		else if(board.getSelectedItem().equals("����ʯ")) {
			 gf.boardkind=7;
			 gf.turn=0;
		}
	}
	
}
