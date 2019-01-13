package main;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Handler 2 here : 

public class QuestionMethods extends JPanel implements ActionListener{
	int correctAns;
	Main trv;	
	int selected=-1;
	boolean answered;
	JPanel panelTrv=new JPanel();//questions
	JPanel panelAns=new JPanel();//answers
        JPanel panelBott=new JPanel();//bottom
	JRadioButton[] responses;
	ButtonGroup group=new ButtonGroup();
	JButton next=new JButton("Next");
	JButton finish=new JButton("Finish");
	
	public QuestionMethods(String q, String[] options, int ans, Main trv){
            //This is a method to display questions and answers.
		this.trv=trv;
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		correctAns=ans;
		//question
		panelTrv.add(new JLabel(q));
		add(panelTrv); 
		//answer
		responses=new JRadioButton[options.length];
		for(int i=0;i<options.length;i++){
                //Use for loop to read the provided answer, then add it to a new panel (with JRadiobutton)
			responses[i]=new JRadioButton(options[i]);      
			responses[i].addActionListener(this);
			group.add(responses[i]);
			panelAns.add(responses[i]);
		}
		add(panelAns);
		//bottom
		next.addActionListener(this);
		finish.addActionListener(this);
		panelBott.add(next);
		panelBott.add(finish);
		add(panelBott);
	}
	
	public void actionPerformed(ActionEvent e){
		Object src=e.getSource();

                if(src.equals(next)){
                    //next button
                    if(selected==-1)
                    {JOptionPane.showMessageDialog(null,"Please choose the answer, "+ trv.getPlayerName() +" .");}
                    
                    else if(selected==correctAns){
                        if(trv.corrects+trv.incorrects==trv.getTrvNums()-1)
                            {
                                trv.corrects++;
				answered=true;
                                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this); //help close questioniare
                                topFrame.setVisible(false);
                                trv.showSummary();
                            }
                        else
                            {   trv.corrects++;
				answered=true;
				trv.next();
                            }
			}
                    else {      
                        
                        if(trv.corrects+trv.incorrects==trv.getTrvNums()-1)
                            {
                                trv.incorrects++;
				answered=true;
                                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this); //help close questioniare
                                topFrame.setVisible(false);
                                trv.showSummary();
                            }
                        else
                            {    trv.incorrects++;
                                 answered=true;
				 trv.next();}
                            }
                
                } //return 0 without selecting anything
		
                if(src.equals(finish)){
		//finish button
                        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this); //help close questioniare
                        topFrame.setVisible(false);
			trv.showSummary();
                        //error when 0 qs answered
		}
                
		for(int i=0;i<responses.length;i++){
                //radio buttons
			if(src==responses[i]){
				selected=i;
			}
		}
	}
	
}