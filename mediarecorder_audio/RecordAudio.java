package selftest.mediarecorder_audio;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class RecordAudio extends AppCompatActivity {
    private Button PlayStart;
    private Button PlayPause;
    private Button PlayStop;
    private Button RecordStart;
    private Button RecordStop;
    File file;
    MediaRecorder mediaRecorder;
    MediaPlayer audio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_audio);
        try {
            file=File.createTempFile("record02",".awr");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mediaRecorder.setOutputFile(file.getAbsolutePath());
        PlayStart=(Button)findViewById(R.id.PlayStart);
        PlayPause=(Button)findViewById(R.id.PlayPause);
        PlayStop=(Button)findViewById(R.id.PlayStop);
        RecordStart=(Button)findViewById(R.id.RecordStart);
        RecordStop=(Button)findViewById(R.id.RecordStop);
    }
    public void RecordStart(View view) throws IOException {
        mediaRecorder.prepare();
        mediaRecorder.start();
        Toast.makeText(this,"录制开始",Toast.LENGTH_SHORT).show();
    }

    public void RecordStop(View view)
    {
        mediaRecorder.stop();
        Toast.makeText(this,"录制结束",Toast.LENGTH_SHORT).show();
        mediaRecorder.release();
    }

    public void PlayStart(View view) throws IOException {
        Toast.makeText(this,file.getAbsolutePath(),Toast.LENGTH_SHORT).show();
        audio=new MediaPlayer();
        audio.setDataSource(file.getAbsolutePath());
        audio.prepare();
        audio.start();
        Toast.makeText(this,"开始播放",Toast.LENGTH_SHORT).show();
    }
    public void PlayPause(View view)
    {
        if(PlayPause.getText().toString().equals("PlayPause"))
        {
            audio.pause();
            Toast.makeText(this,"暂停播放",Toast.LENGTH_SHORT).show();
            PlayPause.setText("PlayRestart");
        }
        else
        {
            audio.start();
            Toast.makeText(this,"继续播放",Toast.LENGTH_SHORT).show();
            PlayPause.setText("PlayPause");
        }
    }
    public void PlayStop(View view)
    {
        audio.stop();
        Toast.makeText(this,"停止播放",Toast.LENGTH_SHORT).show();
        audio.release();
    }
}
