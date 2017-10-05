# MediaRecorder之音频录制
## 状态介绍
* Initial：初始状态，当使用new()方法创建一个MediaRecorder对象或者调用了reset()方法时，该MediaRecorder对象处于Initial状态。在设定视频源或者音频源之后将转换为Initialized状态。另外，在除Released状态外的其它状态通过调用reset()方法都可以使MediaRecorder进入该状态。
* Initialized：已初始化状态，可以通过在Initial状态调用setAudioSource()或setVideoSource()方法进入该状态。在这个状态可以通过setOutputFormat()方法设置输出格式，此时MediaRecorder转换为DataSourceConfigured状态。另外，通过reset()方法进入Initial状态。
* DataSourceConfigured：数据源配置状态，这期间可以设定编码方式、输出文件、屏幕旋转、预览显示等等。可以在Initialized状态通过setOutputFormat()方法进入该状态。另外，可以通过reset()方法回到Initial状态，或者通过prepare()方法到达Prepared状态。
* Prepared：就绪状态，在DataSourceConfigured状态通过prepare()方法进入该状态。在这个状态可以通过start()进入录制状态。另外，可以通过reset()方法回到Initialized状态。
* Recording：录制状态，可以在Prepared状态通过调用start()方法进入该状态。另外，它可以通过stop()方法或reset()方法回到Initial状态。
* Released：释放状态（官方文档给出的词叫做Idle state 空闲状态），可以通过在Initial状态调用release()方法来进入这个状态，这时将会释放所有和MediaRecorder对象绑定的资源。
* Error：错误状态，当错误发生的时候进入这个状态，它可以通过reset()方法进入Initial状态。
## MediaRecorder的生命周期
* 创建->setDatasourse->prepare->start->pause->start->stop->release
* 调用stop进入停止状态,再播放要先调用prepare再调用start
#MediaPlayer的使用方法
## 获得MediaPlayer实例
### 方式一：直接new
### MediaPlayer mp = new MediaPlayer();
### 方式二：使用create方式
### MediaPlayer mp = MediaPlayer.create(this, R.raw.test);//这时就不用调用setDataSource了
## 设置要播放的文件
### MediaPlayer要播放的文件主要包括3个来源：
#### a. 用户在应用中事先自带的resource资源
##### 例如：MediaPlayer.create(this, R.raw.test);
#### b. 存储在SD卡或其他文件路径下的媒体文件
##### 例如：mp.setDataSource("/sdcard/test.mp3");
#### c. 网络上的媒体文件
##### 例如：mp.setDataSource("http://www.citynorth.cn/music/confucius.mp3");
#### MediaPlayer的setDataSource一共四个方法：
* setDataSource (String path)
* setDataSource (FileDescriptor fd)
* setDataSource (Context context, Uri uri)
* setDataSource (FileDescriptor fd, long offset, long length)
## 播放器的主要控制方法
### Android通过控制播放器的状态的方式来控制媒体文件的播放，其中：
#### prepare()和prepareAsync()  提供了同步和异步两种方式设置播放器进入prepare状态，需要注意的是，如果MediaPlayer实例是由create方法创建的，那么第一次启动播放前不需要再调用prepare（）了，因为create方法里已经调用过了。
#### start()是真正启动文件播放的方法，
#### pause()和stop()比较简单，起到暂停和停止播放的作用，
#### seekTo()是定位方法，可以让播放器从指定的位置开始播放，需要注意的是该方法是个异步方法，也就是说该方法返回时并不意味着定位完成，尤其是播放的网络文件，真正定位完成时会触发OnSeekComplete.onSeekComplete()，如果需要是可以调用setOnSeekCompleteListener(OnSeekCompleteListener)设置监听器来处理的。
#### release()可以释放播放器占用的资源，一旦确定不再使用播放器时应当尽早调用它释放资源。
#### reset()可以使播放器从Error状态中恢复过来，重新会到Idle状态。
## 播放器的监听器
###  MediaPlayer提供了一些设置不同监听器的方法来更好地对播放器的工作状态进行监听，以期及时处理各种情况
#### 如： setOnCompletionListener(MediaPlayer.OnCompletionListener listener)、setOnErrorListener(MediaPlayer.OnErrorListener listener)等,设置播放器时需要考虑到播放器可能出现的情况设置好监听和处理逻辑，以保持播放器的健壮性。
