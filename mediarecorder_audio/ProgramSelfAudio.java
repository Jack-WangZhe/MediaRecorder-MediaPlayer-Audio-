package selftest.mediarecorder_audio;

import android.icu.text.UnicodeSetSpanner;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class ProgramSelfAudio extends AppCompatActivity {
    private Button Start;
    private Button Pause;
    private Button Stop;
    MediaPlayer audio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_self_audio);
        Start=(Button)findViewById(R.id.start);
        Pause=(Button)findViewById(R.id.pause);
        Stop=(Button)findViewById(R.id.Stop);
    }
    public void Start(View view) throws IOException {
        audio=MediaPlayer.create(this,R.raw.times);  //自带的resource资源，注意：无 audio.prepare();
        audio.start();
        Toast.makeText(this,"开始播放",Toast.LENGTH_SHORT).show();
    }
    public void Pause(View view)
    {
        if(Pause.getText().toString().equals("Pause"))
        {
            audio.pause();
            Toast.makeText(this,"暂停播放",Toast.LENGTH_SHORT).show();
            Pause.setText("Restart");
        }
        else
        {
            audio.start();
            Toast.makeText(this,"继续播放",Toast.LENGTH_SHORT).show();
            Pause.setText("Pause");
        }
    }
    public void Stop(View view)
    {
        audio.stop();
        Toast.makeText(this,"停止播放",Toast.LENGTH_SHORT).show();
        audio.release();
    }
}
