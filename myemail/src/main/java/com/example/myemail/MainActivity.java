package com.example.myemail;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.media.tv.TvInputService;
import android.os.Handler;
import android.provider.ContactsContract;
import android.service.textservice.SpellCheckerService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sun.mail.smtp.SMTPTransport;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.activation.*;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.event.ConnectionEvent;
import javax.mail.event.ConnectionListener;
import javax.mail.event.MessageChangedEvent;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static javax.mail.Message.*;

public class MainActivity extends AppCompatActivity {

    private Session session;
    private Message msg;
    private Properties props;

    //两种发送邮件方式

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thread.start();

    }




    private void sendByIntent() {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        //设置对方邮件地址
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"abc@163.com",
                "edf@com.cn"});
        //设置标题内容
        intent.putExtra(Intent.EXTRA_SUBJECT, "TEST");
        //启动一个新的Activity，"Sending mail..." 是在启动这个ACTIVITY的等待时间时所显示的文字
        startActivity(Intent.createChooser(intent, "Sending mail......."));

        ((TextView) findViewById(R.id.bt_sendEmailByMail))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

    }


    private Thread thread = new Thread() {
        @Override
        public void run() {


            try {
                send1();
                protocol();
                //handler.sendEmptyMessage(0);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        }
    };


    //定义Handler对象
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message m) {
            super.handleMessage(m);



        }
    };

    private void protocol() {

        try {
            session = Session.getInstance(props,null);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.qq.com","1171546977", "beqaofqqwyeqgbff");
            Address[] allRecipients = msg.getAllRecipients();
            transport.sendMessage(msg,allRecipients);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((Button)findViewById(R.id.bt_sendEmailByMail)).setText("发送成功");
                }
            });


        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {

        }
    }




    private void send1() throws MessagingException {

        props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.password", "beqaofqqwyeqgbff");
       // props.setProperty("mail.smtp.timeout", "25"); 不能轻易设置超时
        props.put("mail.smtp.auth", "true");
        final String username = "1171546977";
        final String password = "beqaofqqwyeqgbff";
        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        // -- Create a new message --
        session.setDebug(true);
        msg = new MimeMessage(session);
        msg.setContent("Hello", "text/plain");
        msg.setHeader("Content-Type", "text/html; charset=UTF-8");
        // -- Set the FROM and TO fields --
        try {
            msg.setFrom(new InternetAddress(username + "@qq.com"));
            msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(
                    "3407462149@qq.com", false));
            msg.setSubject("Title");
            msg.saveChanges();



        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }


}
