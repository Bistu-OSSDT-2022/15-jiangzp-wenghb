import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.Dimension;

//ʵ�ֶ�GoBangframe�������ļ����ӿڴ���
public class frameListener implements GoBangconfig,MouseListener{
	int tx=0;
	int ty=0;
	public GoBangframe gf;
	GoBangconfig go;
	
	public void setGraphics(GoBangframe gf) {
		this.gf=gf;
	}
	
	//AI�����㷨
	public Integer unionWeight(Integer a,Integer b ) {
		//����Ҫ���ж�a,b������ֵ�ǲ���null
		if((a==null)||(b==null)) return 0;
		//һһ:101/202
	    else if((a>=22)&&(a<=25)&&(b>=22)&&(b<=25)) return 60;
		//һ������һ:1011/2022
		else if(((a>=22)&&(a<=25)&&(b>=76)&&(b<=80))||((a>=76)&&(a<=80)&&(b>=22)&&(b<=25))) return 800;
		//һ������һ������:10111/20222
		else if(((a>=10)&&(a<=25)&&(b>=1050)&&(b<=1100))||((a>=1050)&&(a<=1100)&&(b>=10)&&(b<=25))||((a>=76)&&(a<=80)&&(b>=76)&&(b<=80)))
			return 3000;
		//����������һ����һ������һ
		else if(((a>=22)&&(a<=25)&&(b>=140)&&(b<=150))||((a>=140)&&(a<=150)&&(b>=22)&&(b<=25))) return 3000;
		//����������:110111
		else if(((a>=76)&&(a<=80)&&(b>=1050)&&(b<=1100))||((a>=1050)&&(a<=1100)&&(b>=76)&&(b<=80))) return 3000;
		else return 0;
	}
	
	  public void mouseClicked(java.awt.event.MouseEvent e) {
		  int x=e.getX();
		  int y=e.getY();
		  //��������Ҫ�������̵��ĸ��������
		  int countx=(x/40)*40+20;
		  int county=(y/40)*40+20;
		  Graphics g=gf.getGraphics();
			//������������������������Ӧ��λ��
		  int Arrayj=(countx-20)/40;
		  int Arrayi=(county-20)/40;
		  
		  if(gf.turn!=0)//�ж��Ƿ���Խ�����Ϸ
		  if(gf.isAvail[Arrayi][Arrayj]!=0) {
			  gf.PopUp("������ʾ","�˴��Ѿ��������ˣ������������ط�");
		  }
		  else {
			  //ѡ�����˶�ս
			  if(gf.ChooseType==0) {	  
				  if(gf.turn==1) {
					  //�Ȼ�ȡҪ��ĵط�
					  g.drawImage(blackchess,countx-size/2, county-size/2, size, size,null);
					  //���õ�ǰλ���Ѿ���������,����Ϊ����
					  gf.isAvail[Arrayi][Arrayj]=1;
					  //�ѵ�ǰ���µ�����λ�ñ����ڶ�̬������
					  gf.ChessPositonList.add(new ChessPosition(Arrayi,Arrayj));
					  gf.turn++;
					  
					  //�ж��Ƿ��Ѿ��������������
					  //���ж�
					  //���Ƚ綨���鷶Χ����ֹԽ��
					  int imin=Arrayi-4,imax=Arrayi+4;
					  if(imin<0) imin=0;
					  if(imax>14) imax=14;
					  int count1=0;//�ж�������������
					  for(int i=imin;i<=imax;i++) {
						  if(gf.isAvail[i][Arrayj]==1) count1++;
						  //����������������ӣ�������û������ʱ�������¿�ʼ����
						  else count1=0;
						  if(count1==5) { 
							  System.out.println("�ڷ�Ӯ");
							  gf.PopUp("��Ϸ���","�ڷ�Ӯ");
							  gf.turn=0;
							  return;
						  }
					  }
					  //���ж�
					  //���Ƚ綨���鷶Χ����ֹԽ��
					  int jmin=Arrayj-4,jmax=Arrayj+4;
					  if(jmin<0) jmin=0;
					  if(jmax>14) jmax=14;
					  int count2=0;//�ж�������������
					  for(int j=jmin;j<=jmax;j++) {
						  if(gf.isAvail[Arrayi][j]==1) count2++;
						  else count2=0;
						  if(count2==5) {
							  System.out.println("�ڷ�Ӯ");
							  gf.PopUp("��Ϸ���","�ڷ�Ӯ");
							  gf.turn=0;
							  return;
						  }
						//����������������ӣ�������û������ʱ�������¿�ʼ����
						  
					  }
					  //135���ж�
					  //���Ƚ綨���鷶Χ����ֹԽ��
					  int count3=0;//�ж�������������
					  for(int i=-4;i<=4;i++) {
						  if((Arrayi+i>=0)&&(Arrayj+i>=0)&&(Arrayi+i<=14)&&(Arrayj+i<=14)) {
							  if(gf.isAvail[Arrayi+i][Arrayj+i]==1) count3++;
								//����������������ӣ�������û������ʱ�������¿�ʼ����
							  else count3=0;
							  if(count3==5) {
								  System.out.println("�ڷ�Ӯ");
								  gf.PopUp("��Ϸ���","�ڷ�Ӯ");
								  gf.turn=0;
								  return;
							  }
						  }
					  }
					  int count4=0;//�ж�������������
					  for(int i=-4;i<=4;i++) {
						  if((Arrayi+i>=0)&&(Arrayj-i>=0)&&(Arrayi+i<=14)&&(Arrayj-i<=14)) {
							  //System.out.print("count4:"+count4);
							  if(gf.isAvail[Arrayi+i][Arrayj-i]==1) count4++;
								//����������������ӣ�������û������ʱ�������¿�ʼ����
							  else count4=0;
							  if(count4==5) {
								  System.out.println("�ڷ�Ӯ");
								  gf.PopUp("��Ϸ���","�ڷ�Ӯ");
								  gf.turn=0;
								  return;
							  }
						  }
					  }
				  }
				  else if(gf.turn==2){
					  g.drawImage(whitechess,countx-size/2, county-size/2, size, size,null);
					  //���õ�ǰλ���Ѿ��������ˣ�����Ϊ����
					  gf.ChessPositonList.add(new ChessPosition(Arrayi,Arrayj));
					  gf.isAvail[Arrayi][Arrayj]=2;
					  gf.turn--;
					 
					  //���ж�
					  //���Ƚ綨���鷶Χ����ֹԽ��
					  int imin=Arrayi-4,imax=Arrayi+4;
					  if(imin<0) imin=0;
					  if(imax>14) imax=14;
					  int count1=0;//�ж�������������
					  for(int i=imin;i<=imax;i++) {
						  if(gf.isAvail[i][Arrayj]==2) count1++;

						//����������������ӣ�������û������ʱ�������¿�ʼ����
						  else count1=0;
						  if(count1==5) {
							  System.out.println("�׷�Ӯ");
							  gf.PopUp("��Ϸ���","�׷�Ӯ");
							  gf.turn=0;
							  return;
						  }
					  }
					  //���ж�
					  //���Ƚ綨���鷶Χ����ֹԽ��
					  int jmin=Arrayj-4,jmax=Arrayj+4;
					  if(jmin<0) jmin=0;
					  if(jmax>14) jmax=14;
					  int count2=0;//�ж�������������
					  for(int j=jmin;j<=jmax;j++) {
						  if(gf.isAvail[Arrayi][j]==2) count2++;
						  //����������������ӣ�������û������ʱ�������¿�ʼ����
						  else count2=0;
						  if(count2==5) {
							  System.out.println("�׷�Ӯ");
							  gf.PopUp("��Ϸ���","�׷�Ӯ");
							  gf.turn=0;
							  return;
						  }
						  
					  }
					  //135���ж�
					  //���Ƚ綨���鷶Χ����ֹԽ��
					  int count3=0;//�ж�������������
					  for(int i=-4;i<=4;i++) {
						  if((Arrayi+i>=0)&&(Arrayj+i>=0)&&(Arrayi+i<=14)&&(Arrayj+i<=14)) {
							  if(gf.isAvail[Arrayi+i][Arrayj+i]==2) count3++;
							  //����������������ӣ�������û������ʱ�������¿�ʼ����
							  else count3=0;
							  if(count3==5) {
								  System.out.println("�׷�Ӯ");
								  gf.PopUp("��Ϸ���","�׷�Ӯ");
								  gf.turn=0;
								  return;
							  }
						  }
					  }
					  int count4=0;//�ж�������������
					  for(int i=-4;i<=4;i++) {
						  if((Arrayi+i>=0)&&(Arrayj-i>=0)&&(Arrayi+i<=14)&&(Arrayj-i<=14)) {
							  if(gf.isAvail[Arrayi+i][Arrayj-i]==2) count4++;
							  //����������������ӣ�������û������ʱ�������¿�ʼ����
							  else count4=0;
							  if(count4==5) {
								  System.out.println("�׷�Ӯ");
								  gf.PopUp("��Ϸ���","�׷�Ӯ");
								  gf.turn=0;
								  return;
							  }
						  }
					  }
				  }
			  }
			 //���ѡ������˻���ս 
			  else if(gf.ChooseType==1) {
				  if(gf.turn==1) {
					  
					  //��������
					  //�Ȼ�ȡҪ��ĵط�
					  g.drawImage(blackchess,countx-size/2, county-size/2, size, size,null);
					  //���õ�ǰλ���Ѿ���������,����Ϊ����
					  gf.isAvail[Arrayi][Arrayj]=1;
					  //�ѵ�ǰ���µ�����λ�ñ����ڶ�̬������
					  gf.ChessPositonList.add(new ChessPosition(Arrayi,Arrayj));
					  gf.turn++;
					  
					  //�ж��Ƿ��Ѿ��������������
					  //���ж�
					  //���Ƚ綨���鷶Χ����ֹԽ��
					  int Blackimin=Arrayi-4,Blackimax=Arrayi+4;
					  if(Blackimin<0) Blackimin=0;
					  if(Blackimax>14) Blackimax=14;
					  int count1=0;//�ж�������������
					  for(int i=Blackimin;i<=Blackimax;i++) {
						  if(gf.isAvail[i][Arrayj]==1) count1++;
						  //����������������ӣ�������û������ʱ�������¿�ʼ����
						  else count1=0;
						  if(count1==5) { 
							  System.out.println("�ڷ�Ӯ");
							  gf.PopUp("��Ϸ���","�ڷ�Ӯ");
							  return;
						  }
					  }
					  //���ж�
					  //���Ƚ綨���鷶Χ����ֹԽ��
					  int Blackjmin=Arrayj-4,Blackjmax=Arrayj+4;
					  if(Blackjmin<0) Blackjmin=0;
					  if(Blackjmax>14) Blackjmax=14;
					  int count2=0;//�ж�������������
					  for(int j=Blackjmin;j<=Blackjmax;j++) {
						  if(gf.isAvail[Arrayi][j]==1) count2++;
						  else count2=0;
						  if(count2==5) {
							  System.out.println("�ڷ�Ӯ");
							  gf.PopUp("��Ϸ���","�ڷ�Ӯ");
							  return;
						  }
						//����������������ӣ�������û������ʱ�������¿�ʼ����
						  
					  }
					  //135���ж�
					  //���Ƚ綨���鷶Χ����ֹԽ��
					  int count3=0;//�ж�������������
					  for(int i=-4;i<=4;i++) {
						  if((Arrayi+i>=0)&&(Arrayj+i>=0)&&(Arrayi+i<=14)&&(Arrayj+i<=14)) {
							  if(gf.isAvail[Arrayi+i][Arrayj+i]==1) count3++;
								//����������������ӣ�������û������ʱ�������¿�ʼ����
							  else count3=0;
							  if(count3==5) {
								  System.out.println("�ڷ�Ӯ");
								  gf.PopUp("��Ϸ���","�ڷ�Ӯ");
								  return;
							  }
						  }
					  }
					  int count4=0;//�ж�������������
					  for(int i=-4;i<=4;i++) {
						  if((Arrayi+i>=0)&&(Arrayj-i>=0)&&(Arrayi+i<=14)&&(Arrayj-i<=14)) {
							  //System.out.print("count4:"+count4);
							  if(gf.isAvail[Arrayi+i][Arrayj-i]==1) count4++;
								//����������������ӣ�������û������ʱ�������¿�ʼ����
							  else count4=0;
							  if(count4==5) {
								  System.out.println("�ڷ�Ӯ");
								  gf.PopUp("��Ϸ���","�ڷ�Ӯ");
								  return;
							  }
						  }
					  }
					  
					  //��������
					  //�ȼ��������λ�õ�Ȩֵ
					  for(int i=0;i<gf.isAvail.length;i++) {
						  for(int j=0;j<gf.isAvail[i].length;j++) {
							  //�����жϵ�ǰλ���Ƿ�Ϊ��
							  if(gf.isAvail[i][j]==0) {
								  //��������
								  String ConnectType="0";
								  int jmin=Math.max(0, j-4);
								  for(int positionj=j-1;positionj>=jmin;positionj--) {
									  //���μ���ǰ�������
									  ConnectType=ConnectType+gf.isAvail[i][positionj];
								  }
								  //��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ����
								  Integer valueleft=gf.map.get(ConnectType);
								  if(valueleft!=null) gf.weightArray[i][j]+=valueleft;
								  
								  //��������
								  ConnectType="0";
								  int jmax=Math.min(14, j+4);
								  for(int positionj=j+1;positionj<=jmax;positionj++) {
									  //���μ���ǰ�������
									  ConnectType=ConnectType+gf.isAvail[i][positionj];
								  }
								  //��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ����
								  Integer valueright=gf.map.get(ConnectType);
								  if(valueright!=null) gf.weightArray[i][j]+=valueright;
								  
								  //�����жϣ��ж���
								  gf.weightArray[i][j]+=unionWeight(valueleft,valueright);
								  
								  //��������
								  ConnectType="0";
								  int imin=Math.max(0, i-4);
								  for(int positioni=i-1;positioni>=imin;positioni--) {
									  //���μ���ǰ�������
									  ConnectType=ConnectType+gf.isAvail[positioni][j];
								  }
								  //��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ����
								  Integer valueup=gf.map.get(ConnectType);
								  if(valueup!=null) gf.weightArray[i][j]+=valueup;
								  
								  //��������
								  ConnectType="0";
								  int imax=Math.min(14, i+4);
								  for(int positioni=i+1;positioni<=imax;positioni++) {
									  //���μ���ǰ�������
									  ConnectType=ConnectType+gf.isAvail[positioni][j];
								  }
								  //��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ����
								  Integer valuedown=gf.map.get(ConnectType);
								  if(valuedown!=null) gf.weightArray[i][j]+=valuedown;
								  
								  //�����жϣ��ж���
								  gf.weightArray[i][j]+=unionWeight(valueup,valuedown);
								  
								  //�����Ϸ�����,i,j,����ȥ��ͬ����
								  ConnectType="0";
								  for(int position=-1;position>=-4;position--) {
									  if((i+position>=0)&&(i+position<=14)&&(j+position>=0)&&(j+position<=14))
									  ConnectType=ConnectType+gf.isAvail[i+position][j+position];
								  }
								  //��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ��
								  Integer valueLeftUp=gf.map.get(ConnectType);
								  if(valueLeftUp!=null) gf.weightArray[i][j]+=valueLeftUp;
								  
								 //�����·�����,i,j,��������ͬ����
								  ConnectType="0";
								  for(int position=1;position<=4;position++) {
									  if((i+position>=0)&&(i+position<=14)&&(j+position>=0)&&(j+position<=14))
									  ConnectType=ConnectType+gf.isAvail[i+position][j+position];
								  }
								  //��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ��
								  Integer valueRightDown=gf.map.get(ConnectType);
								  if(valueRightDown!=null) gf.weightArray[i][j]+=valueRightDown;
								  
								  //�����жϣ��ж���
								  gf.weightArray[i][j]+=unionWeight(valueLeftUp,valueRightDown);
								  
								  //�����·�����,i��,j��
								  ConnectType="0";
								  for(int position=1;position<=4;position++) {
									  if((i+position>=0)&&(i+position<=14)&&(j-position>=0)&&(j-position<=14))
									  ConnectType=ConnectType+gf.isAvail[i+position][j-position];
								  }
								  //��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ��
								  Integer valueLeftDown=gf.map.get(ConnectType);
								  if(valueLeftDown!=null) gf.weightArray[i][j]+=valueLeftDown;
								  
								  //�����Ϸ�����,i��,j��
								  ConnectType="0";
								  for(int position=1;position<=4;position++) {
									  if((i-position>=0)&&(i-position<=14)&&(j+position>=0)&&(j+position<=14))
									  ConnectType=ConnectType+gf.isAvail[i-position][j+position];
								  }
								  //��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ��
								  Integer valueRightUp=gf.map.get(ConnectType);
								  if(valueRightUp!=null) gf.weightArray[i][j]+=valueRightUp;
								  
								  //�����жϣ��ж���
								  gf.weightArray[i][j]+=unionWeight(valueLeftDown,valueRightUp);
							  }
						  }
					  }
					  
					  //ȡ������Ȩֵ
					  int AIi=0,AIj=0;
					  int weightmax=0;
					  for(int i=0;i<go.row;i++) {
						  for(int j=0;j<go.column;j++) {
							  if(weightmax<gf.weightArray[i][j]) {
								  weightmax=gf.weightArray[i][j];
								  AIi=i;
								  AIj=j;
							  }
						  }
					  }
					  
					  //ȷ��λ�ã�����
					  //i��Ӧy��j��Ӧx
					  countx=20+AIj*40;
					  county=20+AIi*40;
					  g.drawImage(whitechess,countx-size/2, county-size/2, size, size,null);
					  //���õ�ǰλ���Ѿ��������ˣ�����Ϊ����
					  gf.ChessPositonList.add(new ChessPosition(AIi,AIj));
					  gf.isAvail[AIi][AIj]=2;
					  gf.turn--;
					 
					  //�����Ժ�����Ȩֵ����weightArray
					  for(int i=0;i<go.column;i++) 
						  for(int j=0;j<go.row;j++)
							  gf.weightArray[i][j]=0;
					  
					  //���ж�
					  //���Ƚ綨���鷶Χ����ֹԽ��
					  int imin=AIi-4,imax=AIi+4;
					  if(imin<0) imin=0;
					  if(imax>14) imax=14;
					  count1=0;//�ж�������������
					  for(int i=imin;i<=imax;i++) {
						  if(gf.isAvail[i][AIj]==2) count1++;

						//����������������ӣ�������û������ʱ�������¿�ʼ����
						  else count1=0;
						  if(count1==5) {
							  System.out.println("�׷�Ӯ");
							  gf.PopUp("��Ϸ���","�׷�Ӯ");
							  gf.turn=0;
							  return;
						  }
					  }
					  //���ж�
					  //���Ƚ綨���鷶Χ����ֹԽ��
					  int jmin=AIj-4,jmax=AIj+4;
					  if(jmin<0) jmin=0;
					  if(jmax>14) jmax=14;
					  count2=0;//�ж�������������
					  for(int j=jmin;j<=jmax;j++) {
						  if(gf.isAvail[AIi][j]==2) count2++;
						  //����������������ӣ�������û������ʱ�������¿�ʼ����
						  else count2=0;
						  if(count2==5) {
							  System.out.println("�׷�Ӯ");
							  gf.PopUp("��Ϸ���","�׷�Ӯ");
							  gf.turn=0;
							  return;
						  }
						  
					  }
					  //135���ж�
					  //���Ƚ綨���鷶Χ����ֹԽ��
					  count3=0;//�ж�������������
					  for(int i=-4;i<=4;i++) {
						  if((AIi+i>=0)&&(AIj+i>=0)&&(AIi+i<=14)&&(AIj+i<=14)) {
							  if(gf.isAvail[AIi+i][AIj+i]==2) count3++;
							  //����������������ӣ�������û������ʱ�������¿�ʼ����
							  else count3=0;
							  if(count3==5) {
								  System.out.println("�׷�Ӯ");
								  gf.PopUp("��Ϸ���","�׷�Ӯ");
								  gf.turn=0;
								  return;
							  }
						  }
					  }
					  count4=0;//�ж�������������
					  for(int i=-4;i<=4;i++) {
						  if((AIi+i>=0)&&(AIj-i>=0)&&(AIi+i<=14)&&(AIj-i<=14)) {
							  if(gf.isAvail[AIi+i][AIj-i]==2) count4++;
							  //����������������ӣ�������û������ʱ�������¿�ʼ����
							  else count4=0;
							  if(count4==5) {
								  System.out.println("�׷�Ӯ");
								  gf.PopUp("��Ϸ���","�׷�Ӯ");
								  gf.turn=0;
								  return;
							  }
						  }
					  }
				  }
			  }
			  // ������ս
			  else if(gf.ChooseType==2) {
				
				if(gf.turn==1) {
					if (ButtonListener.firstcheck == 0) {
						ButtonListener.firstcheck++;
						//�Ȼ�ȡҪ��ĵط�
						g.drawImage(blackchess,countx-size/2, county-size/2, size, size,null);
						//���õ�ǰλ���Ѿ���������,����Ϊ����
						gf.isAvail[Arrayi][Arrayj]=1;
						//�ѵ�ǰ���µ�����λ�ñ����ڶ�̬������
						gf.ChessPositonList.add(new ChessPosition(Arrayi,Arrayj));
						gf.turn++;
						if(judgeWhite(Arrayi, Arrayj)==0) return;


						algorithmA(2);
						if(judgeWhite(tx, ty)==0) return;
						gf.turn--;
					}
					else {
						algorithmC(1);
						gf.turn++;
						if(judgeBlack(tx, ty)==0) return;
						
						algorithmA(2);
						if(judgeWhite(tx, ty)==0) return;
						gf.turn--;
					}
					
				}
			  }
		  }
	  }
	  
	  public int judgeBlack(int AIi, int AIj) {
			// 0 Ϊ��Ϸ����
			// 1 Ϊ����
			//���ж�
			//���Ƚ綨���鷶Χ����ֹԽ��
			int imin=AIi-4,imax=AIi+4;
			if(imin<0) imin=0;
			if(imax>14) imax=14;
			int count1=0;//�ж�������������
			for(int i=imin;i<=imax;i++) {
				if(gf.isAvail[i][AIj]==1) count1++;
	
			//����������������ӣ�������û������ʱ�������¿�ʼ����
				else count1=0;
				if(count1==5) {
					System.out.println("�ڷ�Ӯ");
					gf.PopUp("��Ϸ���","�ڷ�Ӯ");
					gf.turn=0;
					return 0;
				}
			}
			//���ж�
			//���Ƚ綨���鷶Χ����ֹԽ��
			int jmin=AIj-4,jmax=AIj+4;
			if(jmin<0) jmin=0;
			if(jmax>14) jmax=14;
			int count2=0;//�ж�������������
			for(int j=jmin;j<=jmax;j++) {
				if(gf.isAvail[AIi][j]==1) count2++;
				//����������������ӣ�������û������ʱ�������¿�ʼ����
				else count2=0;
				if(count2==5) {
					System.out.println("�ڷ�Ӯ");
					gf.PopUp("��Ϸ���","�ڷ�Ӯ");
					gf.turn=0;
					return 0;
				}
				
			}
			//135���ж�
			//���Ƚ綨���鷶Χ����ֹԽ��
			int count3=0;//�ж�������������
			for(int i=-4;i<=4;i++) {
				if((AIi+i>=0)&&(AIj+i>=0)&&(AIi+i<=14)&&(AIj+i<=14)) {
					if(gf.isAvail[AIi+i][AIj+i]==1) count3++;
					//����������������ӣ�������û������ʱ�������¿�ʼ����
					else count3=0;
					if(count3==5) {
						System.out.println("�ڷ�Ӯ");
						gf.PopUp("��Ϸ���","�ڷ�Ӯ");
						gf.turn=0;
						return 0;
					}
				}
			}
			int count4=0;//�ж�������������
			for(int i=-4;i<=4;i++) {
				if((AIi+i>=0)&&(AIj-i>=0)&&(AIi+i<=14)&&(AIj-i<=14)) {
					if(gf.isAvail[AIi+i][AIj-i]==1) count4++;
					//����������������ӣ�������û������ʱ�������¿�ʼ����
					else count4=0;
					if(count4==5) {
						System.out.println("�ڷ�Ӯ");
						gf.PopUp("��Ϸ���","�ڷ�Ӯ");
						gf.turn=0;
						return 0;
					}
				}
			}
			return 1; 
		}
	
		public int judgeWhite(int AIi, int AIj) {
			//���ж�
			//���Ƚ綨���鷶Χ����ֹԽ��
			int imin=AIi-4,imax=AIi+4;
			if(imin<0) imin=0;
			if(imax>14) imax=14;
			int count1=0;//�ж�������������
			for(int i=imin;i<=imax;i++) {
				if(gf.isAvail[i][AIj]==2) count1++;
	
			//����������������ӣ�������û������ʱ�������¿�ʼ����
				else count1=0;
				if(count1==5) {
					System.out.println("�׷�Ӯ");
					gf.PopUp("��Ϸ���","�׷�Ӯ");
					gf.turn=0;
					return 0;
				}
			}
			//���ж�
			//���Ƚ綨���鷶Χ����ֹԽ��
			int jmin=AIj-4,jmax=AIj+4;
			if(jmin<0) jmin=0;
			if(jmax>14) jmax=14;
			int count2=0;//�ж�������������
			for(int j=jmin;j<=jmax;j++) {
				if(gf.isAvail[AIi][j]==2) count2++;
				//����������������ӣ�������û������ʱ�������¿�ʼ����
				else count2=0;
				if(count2==5) {
					System.out.println("�׷�Ӯ");
					gf.PopUp("��Ϸ���","�׷�Ӯ");
					gf.turn=0;
					return 0;
				}
				
			}
			//135���ж�
			//���Ƚ綨���鷶Χ����ֹԽ��
			int count3=0;//�ж�������������
			for(int i=-4;i<=4;i++) {
				if((AIi+i>=0)&&(AIj+i>=0)&&(AIi+i<=14)&&(AIj+i<=14)) {
					if(gf.isAvail[AIi+i][AIj+i]==2) count3++;
					//����������������ӣ�������û������ʱ�������¿�ʼ����
					else count3=0;
					if(count3==5) {
						System.out.println("�׷�Ӯ");
						gf.PopUp("��Ϸ���","�׷�Ӯ");
						gf.turn=0;
						return 0;
					}
				}
			}
			int count4=0;//�ж�������������
			for(int i=-4;i<=4;i++) {
				if((AIi+i>=0)&&(AIj-i>=0)&&(AIi+i<=14)&&(AIj-i<=14)) {
					if(gf.isAvail[AIi+i][AIj-i]==2) count4++;
					//����������������ӣ�������û������ʱ�������¿�ʼ����
					else count4=0;
					if(count4==5) {
						System.out.println("�׷�Ӯ");
						gf.PopUp("��Ϸ���","�׷�Ӯ");
						gf.turn=0;
						return 0;
					}
				}
			}
			return 1;
		}
		// AI�����㷨
		public void algorithmA(int chesscolor) {
			//��������
			//�ȼ��������λ�õ�Ȩֵ
			for(int i=0;i<gf.isAvail.length;i++) {
				for(int j=0;j<gf.isAvail[i].length;j++) {
					//�����жϵ�ǰλ���Ƿ�Ϊ��
					if(gf.isAvail[i][j]==0) {
						//��������
						String ConnectType="0";
						int jmin=Math.max(0, j-4);
						for(int positionj=j-1;positionj>=jmin;positionj--) {
							//���μ���ǰ�������
							ConnectType=ConnectType+gf.isAvail[i][positionj];
						}
						//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ����
						Integer valueleft=gf.map.get(ConnectType);
						if(valueleft!=null) gf.weightArray[i][j]+=valueleft;
						
						//��������
						ConnectType="0";
						int jmax=Math.min(14, j+4);
						for(int positionj=j+1;positionj<=jmax;positionj++) {
							//���μ���ǰ�������
							ConnectType=ConnectType+gf.isAvail[i][positionj];
						}
						//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ����
						Integer valueright=gf.map.get(ConnectType);
						if(valueright!=null) gf.weightArray[i][j]+=valueright;
						
						//�����жϣ��ж���
						gf.weightArray[i][j]+=unionWeight(valueleft,valueright);
						
						//��������
						ConnectType="0";
						int imin=Math.max(0, i-4);
						for(int positioni=i-1;positioni>=imin;positioni--) {
							//���μ���ǰ�������
							ConnectType=ConnectType+gf.isAvail[positioni][j];
						}
						//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ����
						Integer valueup=gf.map.get(ConnectType);
						if(valueup!=null) gf.weightArray[i][j]+=valueup;
						
						//��������
						ConnectType="0";
						int imax=Math.min(14, i+4);
						for(int positioni=i+1;positioni<=imax;positioni++) {
							//���μ���ǰ�������
							ConnectType=ConnectType+gf.isAvail[positioni][j];
						}
						//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ����
						Integer valuedown=gf.map.get(ConnectType);
						if(valuedown!=null) gf.weightArray[i][j]+=valuedown;
						
						//�����жϣ��ж���
						gf.weightArray[i][j]+=unionWeight(valueup,valuedown);
						
						//�����Ϸ�����,i,j,����ȥ��ͬ����
						ConnectType="0";
						for(int position=-1;position>=-4;position--) {
							if((i+position>=0)&&(i+position<=14)&&(j+position>=0)&&(j+position<=14))
							ConnectType=ConnectType+gf.isAvail[i+position][j+position];
						}
						//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ��
						Integer valueLeftUp=gf.map.get(ConnectType);
						if(valueLeftUp!=null) gf.weightArray[i][j]+=valueLeftUp;
						
					   //�����·�����,i,j,��������ͬ����
						ConnectType="0";
						for(int position=1;position<=4;position++) {
							if((i+position>=0)&&(i+position<=14)&&(j+position>=0)&&(j+position<=14))
							ConnectType=ConnectType+gf.isAvail[i+position][j+position];
						}
						//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ��
						Integer valueRightDown=gf.map.get(ConnectType);
						if(valueRightDown!=null) gf.weightArray[i][j]+=valueRightDown;
						
						//�����жϣ��ж���
						gf.weightArray[i][j]+=unionWeight(valueLeftUp,valueRightDown);
						
						//�����·�����,i��,j��
						ConnectType="0";
						for(int position=1;position<=4;position++) {
							if((i+position>=0)&&(i+position<=14)&&(j-position>=0)&&(j-position<=14))
							ConnectType=ConnectType+gf.isAvail[i+position][j-position];
						}
						//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ��
						Integer valueLeftDown=gf.map.get(ConnectType);
						if(valueLeftDown!=null) gf.weightArray[i][j]+=valueLeftDown;
						
						//�����Ϸ�����,i��,j��
						ConnectType="0";
						for(int position=1;position<=4;position++) {
							if((i-position>=0)&&(i-position<=14)&&(j+position>=0)&&(j+position<=14))
							ConnectType=ConnectType+gf.isAvail[i-position][j+position];
						}
						//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ��
						Integer valueRightUp=gf.map.get(ConnectType);
						if(valueRightUp!=null) gf.weightArray[i][j]+=valueRightUp;
						
						//�����жϣ��ж���
						gf.weightArray[i][j]+=unionWeight(valueLeftDown,valueRightUp);
					}
				}
			}
			
			//ȡ������Ȩֵ
			int AIi=0,AIj=0;
			int weightmax=0;
			for(int i=0;i<go.row;i++) {
				for(int j=0;j<go.column;j++) {
					if(weightmax<gf.weightArray[i][j]) {
						weightmax=gf.weightArray[i][j];
						AIi=i;
						AIj=j;
					}
				}
			}
			
			//ȷ��λ�ã�����
			//i��Ӧy��j��Ӧx
			int countx=20+AIj*40;
			int county=20+AIi*40;
			if(chesscolor == 1) {
				gf.getGraphics().drawImage(blackchess,countx-size/2, county-size/2, size, size,null);
				
			} else {
				gf.getGraphics().drawImage(whitechess,countx-size/2, county-size/2, size, size,null);
			}
			//���õ�ǰλ���Ѿ���������
			gf.ChessPositonList.add(new ChessPosition(AIi,AIj));
			gf.isAvail[AIi][AIj]=chesscolor;
			tx = AIi;
			ty = AIj;
		   
			//�����Ժ�����Ȩֵ����weightArray
			for(int i=0;i<go.column;i++) 
				for(int j=0;j<go.row;j++)
					gf.weightArray[i][j]=0;
			
		}
		
		// ֱ�������㷨
		public void algorithmB(int chesscolor) {
			//��������
			//�ȼ��������λ�õ�Ȩֵ
			for(int i=0;i<gf.isAvail.length;i++) {
				for(int j=0;j<gf.isAvail[i].length;j++) {
					//�����жϵ�ǰλ���Ƿ�Ϊ��
					if(gf.isAvail[i][j]==0) {
						//��������
						String ConnectType="0";
						int jmin=Math.max(0, j-4);
						for(int positionj=j-1;positionj>=jmin;positionj--) {
							//���μ���ǰ�������
							ConnectType=ConnectType+gf.isAvail[i][positionj];
						}
						//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ����
						Integer valueleft=gf.map.get(ConnectType);
						if(valueleft!=null) gf.weightArray[i][j]+=valueleft;
						
						//��������
						ConnectType="0";
						int jmax=Math.min(14, j+4);
						for(int positionj=j+1;positionj<=jmax;positionj++) {
							//���μ���ǰ�������
							ConnectType=ConnectType+gf.isAvail[i][positionj];
						}
						//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ����
						Integer valueright=gf.map.get(ConnectType);
						if(valueright!=null) gf.weightArray[i][j]+=valueright;
						
						//��������
						ConnectType="0";
						int imin=Math.max(0, i-4);
						for(int positioni=i-1;positioni>=imin;positioni--) {
							//���μ���ǰ�������
							ConnectType=ConnectType+gf.isAvail[positioni][j];
						}
						//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ����
						Integer valueup=gf.map.get(ConnectType);
						if(valueup!=null) gf.weightArray[i][j]+=valueup;
						
						//��������
						ConnectType="0";
						int imax=Math.min(14, i+4);
						for(int positioni=i+1;positioni<=imax;positioni++) {
							//���μ���ǰ�������
							ConnectType=ConnectType+gf.isAvail[positioni][j];
						}
						//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ����
						Integer valuedown=gf.map.get(ConnectType);
						if(valuedown!=null) gf.weightArray[i][j]+=valuedown;
						
						//�����Ϸ�����,i,j,����ȥ��ͬ����
						ConnectType="0";
						for(int position=-1;position>=-4;position--) {
							if((i+position>=0)&&(i+position<=14)&&(j+position>=0)&&(j+position<=14))
							ConnectType=ConnectType+gf.isAvail[i+position][j+position];
						}
						//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ��
						Integer valueLeftUp=gf.map.get(ConnectType);
						if(valueLeftUp!=null) gf.weightArray[i][j]+=valueLeftUp;
						
					   //�����·�����,i,j,��������ͬ����
						ConnectType="0";
						for(int position=1;position<=4;position++) {
							if((i+position>=0)&&(i+position<=14)&&(j+position>=0)&&(j+position<=14))
							ConnectType=ConnectType+gf.isAvail[i+position][j+position];
						}
						//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ��
						Integer valueRightDown=gf.map.get(ConnectType);
						if(valueRightDown!=null) gf.weightArray[i][j]+=valueRightDown;
						
						//�����·�����,i��,j��
						ConnectType="0";
						for(int position=1;position<=4;position++) {
							if((i+position>=0)&&(i+position<=14)&&(j-position>=0)&&(j-position<=14))
							ConnectType=ConnectType+gf.isAvail[i+position][j-position];
						}
						//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ��
						Integer valueLeftDown=gf.map.get(ConnectType);
						if(valueLeftDown!=null) gf.weightArray[i][j]+=valueLeftDown;
						
						//�����Ϸ�����,i��,j��
						ConnectType="0";
						for(int position=1;position<=4;position++) {
							if((i-position>=0)&&(i-position<=14)&&(j+position>=0)&&(j+position<=14))
							ConnectType=ConnectType+gf.isAvail[i-position][j+position];
						}
						//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ��
						Integer valueRightUp=gf.map.get(ConnectType);
						if(valueRightUp!=null) gf.weightArray[i][j]+=valueRightUp;	
					}
				}
			}
			
			//ȡ������Ȩֵ
			int AIi=0,AIj=0;
			int weightmax=0;
			for(int i=0;i<go.row;i++) {
				for(int j=0;j<go.column;j++) {
					if(weightmax<gf.weightArray[i][j]) {
						weightmax=gf.weightArray[i][j];
						AIi=i;
						AIj=j;
					}
				}
			}
			
			//ȷ��λ�ã�����
			//i��Ӧy��j��Ӧx
			int countx=20+AIj*40;
			int county=20+AIi*40;
			if(chesscolor == 1) {
				gf.getGraphics().drawImage(blackchess,countx-size/2, county-size/2, size, size,null);
				
			} else {
				gf.getGraphics().drawImage(whitechess,countx-size/2, county-size/2, size, size,null);
			}
			//���õ�ǰλ���Ѿ���������
			gf.ChessPositonList.add(new ChessPosition(AIi,AIj));
			gf.isAvail[AIi][AIj]=chesscolor;
			tx = AIi;
			ty = AIj;
		   
			//�����Ժ�����Ȩֵ����weightArray
			for(int i=0;i<go.column;i++) 
				for(int j=0;j<go.row;j++)
					gf.weightArray[i][j]=0;
		}
		
		// ����������������(�˹������㷨)
		public void algorithmC(int chesscolor) {
			for(int i=0;i<gf.isAvail.length;i++) {
				for(int j=0;j<gf.isAvail[i].length;j++) {
					if(gf.isAvail[i][j]==0) {
						findxy(i, j, chesscolor);
					}
				}
			}
			//ȡ������Ȩֵ
			int AIi=0,AIj=0;
			int weightmax=0;
			for(int i=0;i<go.row;i++) {
				for(int j=0;j<go.column;j++) {
					if(weightmax<gf.weightArray[i][j]) {
						weightmax=gf.weightArray[i][j];
						AIi=i;
						AIj=j;
					}
				}
			}
			
			//ȷ��λ�ã�����
			//i��Ӧy��j��Ӧx
			int countx=20+AIj*40;
			int county=20+AIi*40;
			if(chesscolor == 1) {
				gf.getGraphics().drawImage(blackchess,countx-size/2, county-size/2, size, size,null);
				
			} else {
				gf.getGraphics().drawImage(whitechess,countx-size/2, county-size/2, size, size,null);
			}
			//���õ�ǰλ���Ѿ���������
			gf.ChessPositonList.add(new ChessPosition(AIi,AIj));
			gf.isAvail[AIi][AIj]=chesscolor;
			tx = AIi;
			ty = AIj;
		   
			//�����Ժ�����Ȩֵ����weightArray
			for(int i=0;i<go.column;i++) 
				for(int j=0;j<go.row;j++)
					gf.weightArray[i][j]=0;
		}

		public void findxy(int AIi, int AIj, int mycolor) {
	
			int imin=Math.max(0,AIi-2);
			int imax=Math.min(AIi+2,14);
			int count1=0;//�ж�������������
			for(int i=imin;i<=imax;i++) {
				if(gf.isAvail[i][AIj]==mycolor) count1++;
			}

			int jmin=Math.max(0,AIj-2);
			int jmax=Math.min(AIj+2,14);
			//�ж�������������
			for(int j=jmin;j<=jmax;j++) {
				if(gf.isAvail[AIi][j]==mycolor) count1++;
			}
			
			//�ж�������������
			for(int i=-2;i<=2;i++) {
				if((AIi+i>=0)&&(AIj+i>=0)&&(AIi+i<=14)&&(AIj+i<=14)) {
					if(gf.isAvail[AIi+i][AIj+i]==mycolor) count1++;
				}
			}
			//�ж�������������
			for(int i=-2;i<=2;i++) {
				if((AIi+i>=0)&&(AIj-i>=0)&&(AIi+i<=14)&&(AIj-i<=14)) {
					if(gf.isAvail[AIi+i][AIj-i]==mycolor) count1++;
				}
			}
			
			gf.weightArray[AIi][AIj] = count1;
		}
	// Method descriptor #8 (Ljava/awt/event/MouseEvent;)V
	  public void mousePressed(java.awt.event.MouseEvent e) {
		  
	  }
	  
	  // Method descriptor #8 (Ljava/awt/event/MouseEvent;)V
	  public void mouseReleased(java.awt.event.MouseEvent e) {
		  
	  }
	  
	  // Method descriptor #8 (Ljava/awt/event/MouseEvent;)V
	  public void mouseEntered(java.awt.event.MouseEvent e) {
		  
	  }
	  
	  // Method descriptor #8 (Ljava/awt/event/MouseEvent;)V
	  public void mouseExited(java.awt.event.MouseEvent e) {
		  
	  }
	
}
