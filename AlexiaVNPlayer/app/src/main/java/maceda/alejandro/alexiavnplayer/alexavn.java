package maceda.alejandro.alexiavnplayer;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.util.*;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.io.*;
import java.text.DateFormat;
import java.util.Calendar;

import android.view.animation.*;
import android.os.Vibrator;
import android.widget.VideoView;

import maceda.alejandro.alexiavnplayer.database.DatabaseConnector;
import maceda.alejandro.alexiavnplayer.preferences.Preferences;


public class alexavn extends Activity {

    private static final String TAG = "Logs de archivos...";
    private TextView tv, character_tv;
    private TextView title_tv;
    private ImageView iv_background, textbox_image, iv_left, iv_center, iv_right, iv_left_big, iv_center_big, iv_right_big;
    private ImageView avatar_iv;
    private Drawable draw;
    private BitmapFactory.Options options, options1;
    private Bitmap bm, bm1;
    private String vn_name = "";
    private String file_path = "";
    public static String file_name = "";
    private String savefile = "";
    private String file_image = "";
    public static long recent_id = 0;
    private String username = "";
    private int startpage = 0;
    private Typewriter writer;
    private FileReader file;
    private LineNumberReader buffer;
    private boolean writing = false;
    private String lastwrite = "";
    private boolean fin = false;
    private LinearLayout ll;
    private int linea;
    private String line;
    private RelativeLayout layout;
    private Animation animFadeIn;
    private Animation animFadeOut;
    private int textSize;
    private int mmthemeId;
    private int themeId;
    private int music_position;
    private VideoView mvideo;
    private int current_line = 0;
    private String current_sound;
    private String current_background;
    private String fechaActual;

    private String saveImageFondo;
    private String saveImageBox;
    private String saveText;
    private String saveImageChar;
    private String saveLineaExacta;

    private String recuperarLineaExacta = "";
    private String recuperarImageFondo = "";
    private String recuperarImageBox = "";
    private String recuperarImageChar = "";
    private String recuperarTexto = "";

    private String comprobar = "";

    private ImageButton btnPreferences;

    private RelativeLayout.LayoutParams lp;

    public MediaPlayer mediaPlayer;
    public SoundPool sp;
    public int flujodemusica;

    private boolean visible = true;
    DatabaseConnector dbConnector;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        vn_name = intent.getStringExtra("vnname");
        file_path = intent.getStringExtra("path");
        file_name = intent.getStringExtra("file");
        savefile = intent.getStringExtra("savefile");
        file_image = intent.getStringExtra("image");
        username = intent.getStringExtra("username");
        recent_id = intent.getLongExtra("recent_id", 0);
        startpage = intent.getIntExtra("start", 1);
        textSize = intent.getIntExtra("textSize", 16);
        recuperarLineaExacta = intent.getStringExtra("saveLineaExacta");
        recuperarImageFondo = intent.getStringExtra("saveImageFondo");
        recuperarImageBox = intent.getStringExtra("saveImageBox");
        recuperarImageChar = intent.getStringExtra("saveImageChar");
        recuperarTexto = intent.getStringExtra("saveText");
        mmthemeId = intent.getIntExtra("theme", android.R.style.Theme_Holo);
        getPreferencesTheme();
        setTheme(themeId);
        super.onCreate(savedInstanceState);
        writer = new Typewriter(this);
        setContentView(R.layout.alexavn);
        tv = (TextView) findViewById(R.id.text_story);
        character_tv = (TextView) findViewById(R.id.text_character);
        character_tv.setTextSize((textSize));
        tv.setTextSize(textSize);
        iv_background = (ImageView) findViewById(R.id.iv);
        iv_left = (ImageView) findViewById(R.id.iv_left);
        iv_center = (ImageView) findViewById(R.id.iv_center);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        iv_left_big = (ImageView) findViewById(R.id.iv_left_big);
        iv_center_big = (ImageView) findViewById(R.id.iv_center_big);
        iv_right_big = (ImageView) findViewById(R.id.iv_right_big);
        textbox_image = (ImageView) findViewById(R.id.textbox_image);
        mvideo = (VideoView) findViewById(R.id.mvideoView);

        //	tv.setText("/sdcard/alexavn/El Principio");
        //Animation
        //	writer.setCharacterDelay(150);
        //	writer.animateText("Prueba Sonido\nPrueba de texto y de pendejadas", 150);
        //	loadPic("storage/sdcard1/DCIM/image/note_big_0078.jpg");
        if (savedInstanceState == null) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //		Toast.makeText(getApplicationContext(),
                    //					   "ImageView: " + iv.getWidth() + " x " + iv.getHeight(), Toast.LENGTH_LONG).show();
                    //		load_image(	"/sdcard/bluetooth/note_big_0051.jpg");
                    readfile();
                }
            }, 200);
        }
	    /*
		sp = new SoundPool (8, AudioManager.STREAM_MUSIC, 0);
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		play_mp();
   		File f = getExternalFilesDir("/storage/sdcard0/VN/BGM/01.mp3");
	
		flujodemusica = sp.load(f.getPath(), 1);
	
		play_sp();
		*/
        //	play_mp();
        //add_button_option();
        layout = (RelativeLayout) findViewById(R.id.RelativeLayout1);
        lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        ll = new LinearLayout(this);

        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setGravity(Gravity.CENTER);
        //	ll.setPadding(300,50,300,50);
        layout.addView(ll, lp);

        animFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        //	show_title("MAMADAS", "red", 22, 22);

    }


    private void preferencias() {
        Intent preferencias = new Intent(this, Preferences.class);
        preferencias.putExtra("vnname", vn_name);
        preferencias.putExtra("path", file_path);
        preferencias.putExtra("file", file_name);
        preferencias.putExtra("savefile", savefile);
        preferencias.putExtra("image", file_image);
        preferencias.putExtra("username", username);
        preferencias.putExtra("recent_id", recent_id);
        preferencias.putExtra("start", startpage);
        preferencias.putExtra("textSize", textSize);
        preferencias.putExtra("saveImageFondo", saveImageFondo);
        preferencias.putExtra("saveImageBox", saveImageBox);
        preferencias.putExtra("saveText", saveText);
        preferencias.putExtra("saveImageChar", saveImageChar);
        preferencias.putExtra("saveLineaExacta", saveLineaExacta);
        startActivity(preferencias);
    }


    private void show_title(String title, String color, int size) {

        title_tv = new TextView(this);
        //btn.setId(id);
        title_tv.setText(title);
        title_tv.setTextSize(size);
        title_tv.setTypeface(null, Typeface.BOLD);
        title_tv.setTextColor(Color.parseColor(color));
        title_tv.setGravity(Gravity.CENTER);

        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setPadding(10, 10, 10, 10);
        ll.addView(title_tv);
        title_tv.startAnimation(animFadeIn);
        //getsize.animateText();

    }

    private void hide_title() {
        title_tv.startAnimation(animFadeOut);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //		Toast.makeText(getApplicationContext(),
                //					   "ImageView: " + iv.getWidth() + " x " + iv.getHeight(), Toast.LENGTH_LONG).show();
                //		load_image(	"/sdcard/bluetooth/note_big_0051.jpg");
                ll.removeView(tv);
                ll.setOrientation(LinearLayout.VERTICAL);
                ll.setPadding(0, 0, 0, 0);

            }
        }, 3000);
    }

    private void show_avatar(String image) {

        avatar_iv = new ImageView(this);
        //btn.setId(id);


        options = new BitmapFactory.Options();
        options.inSampleSize = 2;//
        bm = BitmapFactory.decodeFile(file_path + "/Scenes/" + image, options);
        avatar_iv.setImageBitmap(bm);
        ll.setGravity(Gravity.BOTTOM);


        ll.setPadding(0, 0, 0, 0);
        ll.addView(avatar_iv);
        ll.setOrientation(LinearLayout.VERTICAL);
        //getsize.animateText();

    }

    private void hide_avatar() {
        ll.removeView(avatar_iv);
    }

    public void full_image(View v) {

        if (visible) {
            tv.setVisibility(View.GONE);
            textbox_image.setVisibility(View.GONE);
            character_tv.setVisibility(View.GONE);
            visible = false;
        } else {
            tv.setVisibility(View.VISIBLE);
            textbox_image.setVisibility(View.VISIBLE);
            character_tv.setVisibility(View.VISIBLE);
            visible = true;
        }
    }

    private void vibrate(int time) {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(time);
    }

    public class Typewriter extends androidx.appcompat.widget.AppCompatTextView {
        private CharSequence mText;
        private int mIndex;
        private long mDelay = 100; //500ms delay

        public Typewriter(Context context) {
            super(context);
        }

        public Typewriter(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        private Handler mHandler = new Handler();
        private Runnable characterAdder = new Runnable() {
            @Override
            public void run() {
                tv.setText(mText.subSequence(0, mIndex++));
                if (mIndex <= mText.length()) {
                    mHandler.postDelayed(characterAdder, mDelay);
                    writing = true;
                } else
                    writing = false;
                //show_toast("Termino");
            }
        };

        public void animateText(CharSequence text, long millis) {

            mDelay = millis;
            mText = text;

            mIndex = 0;
            tv.setText("");
            mHandler.removeCallbacks(characterAdder);
            mHandler.postDelayed(characterAdder, mDelay);
        }

        //	public void setCharacterDelay (long millis) {
        //			mDelay = millis;
        //	}
    }

    public Bitmap decodeSampledBitmapFromUri(String uri, int reqWidth, int reqHeight) {

        Bitmap bm = null;

        //try{
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(uri, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(uri, options);
        //	} catch (FileNotFoundException e) {
        //	e.printStackTrace();
        //	Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        //	}

        return bm;
    }

    public int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }

    @Override
    public void onResume() {
        super.onResume();


        resume_mp();
		/*	
		Bitmap bitmap;
		bitmap = decodeSampledBitmapFromUri(
			"/storage/sdcard1/DCIM/image/note_big_0078.jpg"	,
			iv.getWidth(), iv.getHeight());

		if(bitmap == null){
			Toast.makeText(getApplicationContext(), "the image data could not be decoded", Toast.LENGTH_LONG).show();

		}else{
			Toast.makeText(getApplicationContext(), 
						   "Decoded Bitmap: " + bitmap.getWidth() + " x " + bitmap.getHeight(), 
						   Toast.LENGTH_LONG).show();
			iv.setImageBitmap(bitmap);
		}			   
		*/
        //	getsize.setCharacterDelay(150);
        //getsize.animateText();


    }

    public void load_image_textbox(String pictexbox) {

        Bitmap bitmap1;
        bitmap1 = decodeSampledBitmapFromUri(file_path + "/Textbox/" + pictexbox,
                textbox_image.getWidth(), textbox_image.getHeight());

        if (bitmap1 == null) {
            Log.d(TAG, "the image data could not be decoded");
        } else {
			/*
			 Toast.makeText(getApplicationContext(), 
			 "Decoded Bitmap: " + bitmap.getWidth() + " x " + bitmap.getHeight(), 
			 Toast.LENGTH_LONG).show();
			 */
            textbox_image.setImageBitmap(bitmap1);
        }
    }

    //capital city preciate
    public void load_image(String pic) {

        Bitmap bitmap;
        bitmap = decodeSampledBitmapFromUri(file_path + "/Scenes/" + pic,
                iv_background.getWidth(), iv_background.getHeight());

        if (bitmap == null) {
            Log.d(TAG, "the image data could not be decoded");
        } else {
			/*
			Toast.makeText(getApplicationContext(), 
						   "Decoded Bitmap: " + bitmap.getWidth() + " x " + bitmap.getHeight(), 
						   Toast.LENGTH_LONG).show();
						   */
            iv_background.setImageBitmap(bitmap);
            current_background = pic;
        }
    }

    public void load_image_left(String pic) {

        Bitmap bitmap;
        bitmap = decodeSampledBitmapFromUri(file_path + "/Chars/" + pic,
                iv_left.getWidth(), iv_left.getHeight());

        if (bitmap == null) {
            Log.d(TAG, "the image data could not be decoded");

        } else {
			/*
			 Toast.makeText(getApplicationContext(), 
			 "Decoded Bitmap: " + bitmap.getWidth() + " x " + bitmap.getHeight(), 
			 Toast.LENGTH_LONG).show();
			 */
            iv_left.setImageBitmap(bitmap);
        }
    }

    public void load_image_center(String pic) {

        Bitmap bitmap;
        bitmap = decodeSampledBitmapFromUri(file_path + "/Chars/" + pic,
                iv_center.getWidth(), iv_center.getHeight());

        if (bitmap == null) {
            Log.d(TAG, "the image data could not be decoded");

        } else {
			/*
			 Toast.makeText(getApplicationContext(), 
			 "Decoded Bitmap: " + bitmap.getWidth() + " x " + bitmap.getHeight(), 
			 Toast.LENGTH_LONG).show();
			 */
            iv_center.setImageBitmap(bitmap);
        }
    }

    public void load_image_right(String pic) {

        Bitmap bitmap;
        bitmap = decodeSampledBitmapFromUri(file_path + "/Chars/" + pic,
                iv_right.getWidth(), iv_right.getHeight());

        if (bitmap == null) {
            Log.d(TAG, "the image data could not be decoded");

        } else {
			/*
			 Toast.makeText(getApplicationContext(), 
			 "Decoded Bitmap: " + bitmap.getWidth() + " x " + bitmap.getHeight(), 
			 Toast.LENGTH_LONG).show();
			 */
            iv_right.setImageBitmap(bitmap);
        }
    }

    public void load_imagebig_left(String pic) {

        Bitmap bitmap;
        bitmap = decodeSampledBitmapFromUri(file_path + "/Chars/" + pic,
                iv_left_big.getWidth(), iv_left_big.getHeight());

        if (bitmap == null) {
            Log.d(TAG, "the image data could not be decoded");

        } else {
			/*
			 Toast.makeText(getApplicationContext(), 
			 "Decoded Bitmap: " + bitmap.getWidth() + " x " + bitmap.getHeight(), 
			 Toast.LENGTH_LONG).show();
			 */
            iv_left_big.setImageBitmap(bitmap);
        }
    }

    public void load_imagebig_center(String pic) {

        Bitmap bitmap;
        bitmap = decodeSampledBitmapFromUri(file_path + "/Chars/" + pic,
                iv_center_big.getWidth(), iv_center_big.getHeight());

        if (bitmap == null) {
            Log.d(TAG, "the image data could not be decoded");

        } else {
			/*
			 Toast.makeText(getApplicationContext(), 
			 "Decoded Bitmap: " + bitmap.getWidth() + " x " + bitmap.getHeight(), 
			 Toast.LENGTH_LONG).show();
			 */
            iv_center_big.setImageBitmap(bitmap);
        }
    }

    public void load_imagebig_right(String pic) {

        Bitmap bitmap;
        bitmap = decodeSampledBitmapFromUri(file_path + "/Chars/" + pic,
                iv_right_big.getWidth(), iv_right_big.getHeight());

        if (bitmap == null) {
            Log.d(TAG, "the image data could not be decoded");

        } else {
			/*
			 Toast.makeText(getApplicationContext(), 
			 "Decoded Bitmap: " + bitmap.getWidth() + " x " + bitmap.getHeight(), 
			 Toast.LENGTH_LONG).show();
			 */
            iv_right_big.setImageBitmap(bitmap);
        }
    }

    private void char_clear_left() {
        iv_left.setImageBitmap(null);
    }

    private void char_clear_center() {
        iv_center.setImageBitmap(null);
    }

    private void char_clear_right() {
        iv_right.setImageBitmap(null);
    }

    private void charbig_clear_left() {
        iv_left_big.setImageBitmap(null);
    }

    private void charbig_clear_center() {
        iv_center_big.setImageBitmap(null);
    }

    private void charbig_clear_right() {
        iv_right_big.setImageBitmap(null);
    }

    private void play_mp(String file_sound) {
        stop_mp();
        String path = file_path + "/BGM/";
        // TODO Auto-generated method stub
        mediaPlayer = new MediaPlayer();


        try {
            //	mediaPlayer.setDataSource(PATH_TO_FILE);

            mediaPlayer.setDataSource(path + file_sound);
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            //	stateMediaPlayer = stateMP_Error;
            //textState.setText("- ERROR!!! -");
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            //	stateMediaPlayer = stateMP_Error;
            //textState.setText("- ERROR!!! -");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            //	stateMediaPlayer = stateMP_Error;
            //textState.setText("- ERROR!!! -");
        }
    }

    private void play_fx(String file_sound) {

        String path = file_path + "/FX/";
        // TODO Auto-generated method stub
        mediaPlayer = new MediaPlayer();


        try {
            //	mediaPlayer.setDataSource(PATH_TO_FILE);
            mediaPlayer.setDataSource(path + file_sound);
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            //	stateMediaPlayer = stateMP_Error;
            //textState.setText("- ERROR!!! -");
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            //	stateMediaPlayer = stateMP_Error;
            //textState.setText("- ERROR!!! -");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            //	stateMediaPlayer = stateMP_Error;
            //textState.setText("- ERROR!!! -");
        }
    }

    private void stop_mp() {
        // TODO Auto-generated method stub
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            music_position = 0;
        }

    }

    public void destroy_mp() {
        if (mediaPlayer != null)
            mediaPlayer.release();
    }


    private void play_sp() {
        // TODO Auto-generated method stub

        sp.play(flujodemusica, 5, 5, 0, 0, 1);
    }

    private void pause_mp() {
        //
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            music_position = mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
        }
    }

    private void resume_mp() {
        if (mediaPlayer != null && mediaPlayer.isPlaying() == false) {
            mediaPlayer.seekTo(music_position);
            mediaPlayer.start();
        }
    }

    private void play_vid(String vid) {
        mvideo.setVisibility(View.VISIBLE);
        iv_background.setVisibility(View.GONE);
        // show_toast("Reproduciendo video...");

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(alexavn.this, Uri.fromFile(new File(vid)));
        String duration_vidd = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        int duration_vid = Integer.parseInt(duration_vidd);

        retriever.release();
        mvideo.setVideoPath(vid);
        //int duration_vid = mvideo.getDuration();
        // show_toast("" + duration_vid);
        mvideo.start();
        mvideo.requestFocus();
		/*
		mvideo.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
				| View.SYSTEM_UI_FLAG_FULLSCREEN
				| View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
				| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		mmHandler.postDelayed(mmyRunnable, duration_vid + 2);

		num_image++;
		*/

    }

    private void stop_vid() {
        if (mvideo.isPlaying()) {
            mvideo.stopPlayback();

        }
        mvideo.setVisibility(View.GONE);
        iv_background.setVisibility(View.VISIBLE);
    }

    private void createcarpeta() {

        File f = new File("/sdcard/alexainventario/backup/");
        f.getAbsolutePath();

        if (f.mkdirs()) {
            //se ha creado bien
            //string.replace(" ", "\\ ");
            show_toast(getString(R.string.carpeta_creada));

        } else {
            if (f.exists()) {
                //	((MainActivity)getActivity()).show_toast("La carpeta ya existe");
            } else
                show_toast(getString(R.string.carpeta_no_encontrada));
        }
    }

    private void load_line() {

        boolean succes = false;
        boolean firstline = true;
        int load = 0;
        try {

            file = new FileReader(file_path + file_name);//ei_path.getText().toString());
            buffer = new LineNumberReader(file);
            String line = "";


            //Strings a lot
            //((MainActivity)getActivity()).show_toast(""+buffer.readLine().length());
//	buffer.readLine().length()
            //db.beginTransaction();
            //	while ((line = buffer.readLine()) != null) {

            load++;
            //		if (firstline) {

            //			}
            //			else {
            //	String[] sarray = line.split(",");
            //	StringBuilder sb =  new StringBuilder(str1);
            //	((MainActivity) getActivity()).show_toast(sarray[0]);
            //	loadProducts(sarray[0], sarray[1], sarray[2], sarray[3], sarray[4], sarray[5], sarray[6], sarray[7],sarray[8], sarray[9]);
            //			}
            firstline = false;
            //	file.

            //		}
            buffer.close();
            succes = true;
            //	((MainActivity) getActivity()).show_toast("Loading complete");
        } catch (Exception ex) {
            Log.d(TAG, getString(R.string.fallo_abrir));
            succes = false;
        } finally {
            if (succes) {
                show_toast(getString(R.string.cargando));

            }

        }
    }

    private void readfile() {
        boolean succes = false;
        //	boolean firstline=true;
        //int loadf=0;
        //int finalize=0;
        try {

            file = new FileReader(file_path + file_name);//ei_path.getText().toString());
            buffer = new LineNumberReader(file);

            String line = "";

            //	buffer.close();
            succes = true;
        } catch (Exception ex) {
            Log.d(TAG, "Open failed");
            succes = false;
        } finally {
            if (succes) {
                //ll.removeAllViewsInLayout();
                ll.removeAllViews();
                fin = false;
                file_path = file_path.replace("Scripts/", "");

                next_line(null);

                //	show_toast("Import: "+loadf+" lineas");
                //	loadCSV();
                //	load=0;
                //	firstline=true;

            }

        }
    }

    public void show_toast(String msg) {
        Toast.makeText(getApplicationContext(),
                msg, Toast.LENGTH_LONG).show();
    }

    public void next_line(View v) {

        //	show_toast("Funkando");
        current_line++;
        if (fin) {

        } else {

            if (writing) {
                writer.animateText("", 0);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //		Toast.makeText(getApplicationContext(),
                        //					   "ImageView: " + iv.getWidth() + " x " + iv.getHeight(), Toast.LENGTH_LONG).show();
                        //		load_image(	"/sdcard/bluetooth/note_big_0051.jpg");
                        tv.setText(lastwrite);
                    }
                }, 80);

            } else {
                try {
			/*
			if (mvideo.isPlaying()) {
				mvideo.stopPlayback();

			}
			*/
                    line = "";
                    //	show_toast(""+buffer.getLineNumber());
                    line = buffer.readLine();
                    //Log.d(TAG, "next_line: " + line);
                    //Log.d(TAG, "Linea exatcta: " + recuperarLineaExacta);


                    String[] separated = line.split("\\+");

                    recuperarDatos(separated);

                } catch (Exception ex) {
                    Log.d(TAG, "Open failed");

                } finally {
                    //	loadCSV();
                    //	load=0;
                    //	firstline=true;

                }
            }
        }
    }


    private void add_button_option(String text, String name_file, String bsize, String txtSize, String bgColor, String tColor) {

        //	ll.removeAllViews();

        final Button btn = new Button(this);

        int tSize = Integer.parseInt(bsize);
        if (tSize > 2000) {
            tSize = 2000;
        }

        int sizeText = Integer.parseInt(txtSize);
        if (sizeText > 60) {
            sizeText = 60;
        }

        //Color de texto
        btn.setTextColor(Color.parseColor(tColor));
        //Color bacground
        btn.setBackgroundColor(Color.parseColor(bgColor));
        //Tamaño de la fuente
        btn.setTextSize(sizeText);


        //btn.setId(id);
        btn.setText(text);
        btn.setTag(name_file);
        btn.setPadding(0, 20, 0, 20);
        btn.setTextSize(22);
        btn.setTypeface(null, Typeface.BOLD);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                tSize,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 20, 0, 10);
        btn.setLayoutParams(params);

			/*
		ViewGroup.LayoutParams params = btn.getLayoutParams();
		//Button new width
		params.width = 50;

		btn.setLayoutParams(params);
		*/


        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //	show_toast(btn.getTag().toString());
                file_path = file_path + "Scripts/";
                file_name = btn.getTag().toString();
                //	show_toast(file_path+file_name);
                readfile();
            }
        });
        // lp.addRule(RelativeLayout.RIGHT_OF, <Id>);
        //ll.setPadding(300,50,300,50);

        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(btn);
    }

    private void add_image_option(String nImage, String name_file, String wSize, String hSize) {

        //	ll.removeAllViews();

        //width - ancho
        //heihght - alto
        final ImageView iBtn = new ImageView(this);

        iBtn.setBackgroundColor(Color.TRANSPARENT);
        iBtn.setImageURI(Uri.parse(file_path + "/Scenes/" + nImage));

        int widthSize = Integer.parseInt(wSize);


        int heightSize = Integer.parseInt(hSize);

        int width = widthSize;
        int height = heightSize;

        if (width > 2000) {
            width = 2000;
        }

        if (height > 500) {
            height = 500;
        }

        iBtn.setPadding(0, 20, 0, 20);
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width, height);
        parms.setMargins(10, 20, 10, 20);
        iBtn.setLayoutParams(parms);

        iBtn.setTag(name_file);


        iBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //	show_toast(btn.getTag().toString());
                file_path = file_path + "Scripts/";
                file_name = iBtn.getTag().toString();
                //	show_toast(file_path+file_name);
                readfile();
            }
        });
        // lp.addRule(RelativeLayout.RIGHT_OF, <Id>);
        //ll.setPadding(300,50,300,50);

        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.addView(iBtn);

    }

    private void getPreferencesTheme() {

        if (mmthemeId == android.R.style.Theme_Holo) {
            themeId = android.R.style.Theme_Holo_NoActionBar_Fullscreen;
        }
        if (mmthemeId == android.R.style.Theme_Holo_Light_DarkActionBar) {
            themeId = android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen;
        }
        if (mmthemeId == android.R.style.Theme_Holo_Light) {
            themeId = android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen;
        }
        if (mmthemeId == android.R.style.Theme_Black) {
            themeId = android.R.style.Theme_Black_NoTitleBar_Fullscreen;
        }
        if (mmthemeId == android.R.style.Theme_Light) {
            themeId = android.R.style.Theme_Light_NoTitleBar_Fullscreen;
        }

    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first

        pause_mp();
    }

    public void onDestroy() {

        super.onDestroy();
        stop_mp();
        destroy_mp();
        if (mvideo.isPlaying()) {
            mvideo.stopPlayback();

        }
    }

    public void updateRecent(final long c_id, final String c_name) {
        dbConnector = new DatabaseConnector(this);

        dbConnector.open();


        @SuppressLint("StaticFieldLeak") AsyncTask<Object, Object, Cursor> insertTask =
                new AsyncTask<Object, Object, Cursor>() {
                    @Override
                    protected Cursor doInBackground(Object... params) {


                        //" (_id integer primary key autoincrement, name, path, file, line, image)
                        dbConnector.updateRecent(c_id, c_name);

                        //dbConnector.open();
                        return null;

                    }

                    @Override
                    protected void onPostExecute(Cursor result) {

                        dbConnector.close();

                    }
                };
        insertTask.execute();

    }

    public void go_settings(View v) {
        preferencias();
    }

    public void save(View view) {
        Calendar calendar = Calendar.getInstance();
        fechaActual = DateFormat.getDateInstance().format(calendar.getTime());
        SharedPreferences preferences = this.getSharedPreferences("saveVNslot1", Context.MODE_PRIVATE);
        String saveImage = file_image;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("date", fechaActual);
        editor.putString("vnname", vn_name);
        editor.putString("path", file_path);
        editor.putString("file", file_name);
        editor.putString("savefile", savefile);
        editor.putString("image", file_image);
        editor.putString("username", username);
        editor.putFloat("recent_id", recent_id);
        editor.putInt("start", startpage);
        editor.putInt("textSize", textSize);
        editor.putString("image1", saveImage);
        editor.putString("saveImageFondo", saveImageFondo);
        editor.putString("saveImageBox", saveImageBox);
        editor.putString("saveText", saveText);
        editor.putString("saveImageChar", saveImageChar);
        editor.putString("saveLineaExacta", saveLineaExacta);
        editor.commit();
        show_toast(getString(R.string.guardando));
    }

    private void recuperarDatos(String[] separated) {
        if (recuperarLineaExacta == null) {
            acciones(separated);
        } else {
            if (recuperarLineaExacta.equals(line)) {
                recuperarLineaExacta = null;
                acciones(separated);
            } else {
                acciones2(separated);
                if (!recuperarImageFondo.equals("")) {
                    load_image(recuperarImageFondo);
                }
                if (!recuperarImageBox.equals("")) {

                    if(recuperarImageBox.equals("No hay datos...")){
                        tv.setBackgroundColor(Color.parseColor("#80000000"));
                    }else{
                        load_image_textbox(recuperarImageBox);
                    }
                }
                if (!recuperarImageChar.equals("")) {
                    load_image_center(recuperarImageChar);
                }
            }
        }

    }

    private void acciones(String[] separated) {
        if (separated[0].equals("[BGM]")) {
            //	show_toast("INICIANDO SONIDO");
            saveLineaExacta = line;
            play_mp(separated[2]);
            next_line(null);
        } else if (separated[0].equals("[VIDEO]")) {
            saveLineaExacta = line;
            //show_toast("Reproduciendo video");
            //next_line(null);
            //mvideo.setVisibility(View.VISIBLE);
            play_vid(file_path + "/Video/" + separated[1]);
        } else if (separated[0].equals("[STOPVIDEO]")) {
            saveLineaExacta = line;
            //show_toast("Reproduciendo video");
            //next_line(null);
            //mvideo.setVisibility(View.VISIBLE);
            stop_vid();
            next_line(null);


        } else if (separated[0].equals("[IMG]")) {
            saveLineaExacta = line;
            //show_toast("CARGANDO IMAGEN");
            saveImageFondo = separated[1];
            load_image(separated[1]);
            next_line(null);
        } else if (separated[0].equals("[CHAR]")) {
            saveLineaExacta = line;
            //show_toast("CARGANDO IMAGEN");
            saveImageChar = separated[2];
            if (separated[1].equals("left")) {
                //show_toast("imagen izquierda");
                load_image_left(separated[2]);
                next_line(null);
            } else if (separated[1].equals("center")) {
                //show_toast("imagen center");
                load_image_center(separated[2]);
                next_line(null);
            } else if (separated[1].equals("right")) {
                //show_toast("imagen derecha");
                load_image_right(separated[2]);
                next_line(null);
            } else
                next_line(null);
            //load_image(separated[1]);
            //next_line(null);
        } else if (separated[0].equals("[CHARBIG]")) {
            saveLineaExacta = line;
            //show_toast("CARGANDO IMAGEN");

            if (separated[1].equals("left")) {
                //	show_toast("imagen izquierda");
                load_imagebig_left(separated[2]);
                next_line(null);
            } else if (separated[1].equals("center")) {
                //show_toast("imagen center");
                load_imagebig_center(separated[2]);
                next_line(null);
            } else if (separated[1].equals("right")) {
                //show_toast("imagen derecha");
                load_imagebig_right(separated[2]);
                next_line(null);
            } else
                next_line(null);
            //load_image(separated[1]);
            //next_line(null);
        } else if (separated[0].equals("[CHARCLEAR]")) {
            saveLineaExacta = line;
            //show_toast("CARGANDO IMAGEN");
            saveImageChar = "";
            if (separated[1].equals("left")) {
                //	show_toast("imagen izquierda");
                char_clear_left();
                next_line(null);
            } else if (separated[1].equals("center")) {
                //show_toast("imagen center");
                //load_imagebig_center(separated[2]);
                char_clear_center();
                next_line(null);
            } else if (separated[1].equals("right")) {
                //show_toast("imagen derecha");
                //load_imagebig_right(separated[2]);
                char_clear_right();
                next_line(null);
            } else
                next_line(null);
            //load_image(separated[1]);
            //next_line(null);
        } else if (separated[0].equals("[CHARBIGCLEAR]")) {
            saveLineaExacta = line;
            //show_toast("CARGANDO IMAGEN");
            if (separated[1].equals("left")) {
                //	show_toast("imagen izquierda");
                charbig_clear_left();
                next_line(null);
            } else if (separated[1].equals("center")) {
                //show_toast("imagen center");
                //load_imagebig_center(separated[2]);
                charbig_clear_center();
                next_line(null);
            } else if (separated[1].equals("right")) {
                //show_toast("imagen derecha");
                //load_imagebig_right(separated[2]);
                charbig_clear_right();
                next_line(null);
            } else
                next_line(null);
            //load_image(separated[1]);
            //next_line(null);
        } else if (separated[0].equals("[SHOWBG]")) {
            saveLineaExacta = line;
            full_image(null);
            next_line(null);
        } else if (separated[0].equals("[WAIT]")) {
            saveLineaExacta = line;
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //		Toast.makeText(getApplicationContext(),
                    //					   "ImageView: " + iv.getWidth() + " x " + iv.getHeight(), Toast.LENGTH_LONG).show();
                    //		load_image(	"/sdcard/bluetooth/note_big_0051.jpg");
                    next_line(null);
                }
            }, Integer.parseInt(separated[1]));
        } else if (separated[0].equals("[CHOICES]")) {
            saveLineaExacta = line;
            //	show_toast(""+separated.length);
            ll.removeAllViews();
            ll.setGravity(Gravity.CENTER);
            int commands = 2;
            int total_options = separated.length;
            int total_buttons = ((total_options - 2) / 3);
            writer.animateText(separated[1], 50);
            for (int i = 0; i < total_buttons; i++) {
                add_button_option(separated[commands], separated[commands + 1], separated[commands + 2], separated[commands + 3], separated[commands + 4], separated[commands + 5]);
                commands = commands + 6;
            }
            fin = true;
        } else if (separated[0].equals("[END]")) {
            //stop_mp();
            show_toast(getString(R.string.fin_novel));
            finish();
            stop_mp();
        } else if (separated[0].equals("[FADEOUT]")) {
            saveLineaExacta = line;
            iv_background.startAnimation(animFadeOut);
            next_line(null);
        } else if (separated[0].equals("[FADEIN]")) {
            saveLineaExacta = line;
            iv_background.startAnimation(animFadeIn);
            next_line(null);
        } else if (separated[0].equals("[STOPSOUND]")) {
            saveLineaExacta = line;
            stop_mp();
            next_line(null);
        } else if (separated[0].equals("[SOUNDFX]")) {
            saveLineaExacta = line;
            play_fx(separated[2]);
            next_line(null);
        } else if (separated[0].equals("[VIBRATE]")) {
            saveLineaExacta = line;
            vibrate(Integer.valueOf(separated[1]));
            next_line(null);
        } else if (separated[0].equals("[TITLE]")) {
            saveLineaExacta = line;
            show_title(separated[1], separated[2], Integer.parseInt(separated[3]));
            //	vibrate(Integer.valueOf(separated[1]));
            next_line(null);
        } else if (separated[0].equals("[TITLEOFF]")) {
            saveLineaExacta = line;

            //	show_title(separated[1], separated[2], Integer.parseInt( separated[3]), Integer.parseInt( separated[4]));
            //	vibrate(Integer.valueOf(separated[1]));
            hide_title();
            next_line(null);
        } else if (separated[0].equals("[AVATAR]")) {
            saveLineaExacta = line;

            show_avatar(separated[1]);
            //	vibrate(Integer.valueOf(separated[1]));
            next_line(null);
        } else if (separated[0].equals("[AVATAROFF]")) {
            saveLineaExacta = line;

            hide_avatar();
            //	vibrate(Integer.valueOf(separated[1]));
            next_line(null);
        } else if (separated[0].equals("[TEXTBOX]")) {
            saveLineaExacta = line;

            //hide_avatar();
            saveImageBox = separated[1];
            load_image_textbox(separated[1]);
            //show_toast("QUESQUE si");
            //	vibrate(Integer.valueOf(separated[1]));
            next_line(null);
        } else if (separated[0].equals("[TEXTBOXCLEAR]")) {
            saveLineaExacta = line;

            //hide_avatar();
            textbox_image.setImageDrawable(null);
            tv.setBackgroundColor(Color.TRANSPARENT);
            //show_toast("QUESQUE si");
            //	vibrate(Integer.valueOf(separated[1]));
            next_line(null);
        } else if (separated[0].equals("[TEXTBOXTRANS]")) {
            saveLineaExacta = line;

            //hide_avatar();
            //textbox_image.setBackgroundColor(Color.alpha(80000000));
            textbox_image.setImageDrawable(null);
            tv.setBackgroundColor(Color.parseColor("#80000000"));
            //tv.setBackgroundColor(
            //show_toast("QUESQUE si");
            //	vibrate(Integer.valueOf(separated[1]));
            next_line(null);
        } else if (separated[0].equals("[TEXTCOLOR]")) {
            saveLineaExacta = line;

            //hide_avatar();
            //textbox_image.setBackgroundColor(Color.alpha(80000000));
            //textbox_image.setImageDrawable(null);
            tv.setTextColor(Color.parseColor(separated[1]));
            //tv.setBackgroundColor(
            //show_toast("QUESQUE si");
            //	vibrate(Integer.valueOf(separated[1]));
            next_line(null);
        } else if (separated[0].equals("[IGNORE]")) {
            saveLineaExacta = line;

            //	show_title(separated[1], separated[2], Integer.parseInt( separated[3]), Integer.parseInt( separated[4]));
            //	vibrate(Integer.valueOf(separated[1]));
            next_line(null);
        } else if (separated[0].equals("[SAVE]")) {
            saveLineaExacta = line;

            //	show_title(separated[1], separated[2], Integer.parseInt( separated[3]), Integer.parseInt( separated[4]));
            //	vibrate(Integer.valueOf(separated[1]));

            //show_toast("id: " + recent_id + " - " + file_name);
            updateRecent(recent_id, file_name);
            //saveRecent(sarray[0], curPath, sarray[1], sarray[2]  );


            next_line(null);
        } else if (separated[0].equals("[NAMECHAR]")) {
            saveLineaExacta = line;
            int sizeText = Integer.parseInt(separated[2]);
            if (sizeText > 40) {
                sizeText = 40;
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(20, 0, 0, 0);
            character_tv.setLayoutParams(params);

            character_tv.setVisibility(View.VISIBLE);
            character_tv.setText(separated[1]);
            //Tamaño del texto
            character_tv.setTextSize(sizeText);
            // Color del texto
            character_tv.setTextColor(Color.parseColor(separated[3]));
            //Color de fondo
            character_tv.setBackgroundColor(Color.parseColor(separated[4]));


            next_line(null);

        } else if (separated[0].equals("[CLEARNAMECHAR]")) {
            saveLineaExacta = line;
            character_tv.setVisibility(View.GONE);
            character_tv.setText("");
            next_line(null);

        } else if (separated[0].equals("[CHOICESIMAGE]")) {
            saveLineaExacta = line;
            ll.removeAllViews();
            ll.setGravity(Gravity.CENTER);
            int commands = 2;
            int total_options = separated.length;
            int total_buttons = ((total_options - 2) / 3);
            writer.animateText(separated[1], 50);
            for (int i = 0; i < total_buttons; i++) {
                add_image_option(separated[commands], separated[commands + 1], separated[commands + 2], separated[commands + 3]);
                commands = commands + 4;
            }
            fin = true;
        } else if (separated[0].equals("[NPLAYER]")) {
            saveLineaExacta = line;

            SharedPreferences preferences = this.getSharedPreferences("Informacion", Context.MODE_PRIVATE);
            String nombre = preferences.getString("nombre", "No existe la informacion");


            writer.animateText(separated[1] + " " + nombre + " " + separated[2], 50);
            //next_line(null);
            String hut = "";
            if (hut.contains("[NPLAYER]")) {
                hut.replace("[NPLAYER]", nombre);
            }
            next_line(null);
        } else


        //	separated[0]; // this will contain "Fruit"
        //separated[1]; /

        {

            if (line != null) {
			    /*
				if (separated.length > 2) {
					character_tv.setVisibility(View.VISIBLE);
					character_tv.setText(separated[1]);
					lastwrite = separated[2];
					writer.animateText(separated[2], 50);
				} else {
			lastwrite = separated[1];
			writer.animateText(separated[1], 50);
			character_tv.setVisibility(View.GONE);
			}

			     */
                saveLineaExacta = line;
                saveText = separated[1];
                lastwrite = separated[1];
                writer.animateText(separated[1], 50);

            } else
                finish();
        }
    }

    private void acciones2(String[] separated) {

        if (separated[0].equals("[BGM]")) {
            next_line(null);
        } else if (separated[0].equals("[VIDEO]")) {
            next_line(null);
        } else if (separated[0].equals("[STOPVIDEO]")) {
            next_line(null);
        } else if (separated[0].equals("[IMG]")) {
            next_line(null);
        } else if (separated[0].equals("[CHAR]")) {
            if (separated[1].equals("left")) {
                next_line(null);
            } else if (separated[1].equals("center")) {
                next_line(null);
            } else if (separated[1].equals("right")) {
                next_line(null);
            } else
                next_line(null);
        } else if (separated[0].equals("[CHARBIG]")) {
            if (separated[1].equals("left")) {
                next_line(null);
            } else if (separated[1].equals("center")) {
                next_line(null);
            } else if (separated[1].equals("right")) {
                next_line(null);
            } else
                next_line(null);
        } else if (separated[0].equals("[CHARCLEAR]")) {
            if (separated[1].equals("left")) {
                next_line(null);
            } else if (separated[1].equals("center")) {
                next_line(null);
            } else if (separated[1].equals("right")) {
                next_line(null);
            } else
                next_line(null);
        } else if (separated[0].equals("[CHARBIGCLEAR]")) {
            if (separated[1].equals("left")) {
                next_line(null);
            } else if (separated[1].equals("center")) {
                next_line(null);
            } else if (separated[1].equals("right")) {
                next_line(null);
            } else
                next_line(null);
        } else if (separated[0].equals("[SHOWBG]")) {
            next_line(null);
        } else if (separated[0].equals("[WAIT]")) {
            next_line(null);
        } else if (separated[0].equals("[CHOICES]")) {
            next_line(null);
        } else if (separated[0].equals("[END]")) {
            show_toast(getString(R.string.fin_novel));
            finish();
            stop_mp();
        } else if (separated[0].equals("[FADEOUT]")) {
            next_line(null);
        } else if (separated[0].equals("[FADEIN]")) {
            next_line(null);
        } else if (separated[0].equals("[STOPSOUND]")) {
            next_line(null);
        } else if (separated[0].equals("[SOUNDFX]")) {
            next_line(null);
        } else if (separated[0].equals("[VIBRATE]")) {
            next_line(null);
        } else if (separated[0].equals("[TITLE]")) {
            next_line(null);
        } else if (separated[0].equals("[TITLEOFF]")) {
            next_line(null);
        } else if (separated[0].equals("[AVATAR]")) {
            next_line(null);
        } else if (separated[0].equals("[AVATAROFF]")) {
            next_line(null);
        } else if (separated[0].equals("[TEXTBOX]")) {
            next_line(null);
        } else if (separated[0].equals("[TEXTBOXCLEAR]")) {
            next_line(null);
        } else if (separated[0].equals("[TEXTBOXTRANS]")) {
            next_line(null);
        } else if (separated[0].equals("[TEXTCOLOR]")) {
            next_line(null);
        } else if (separated[0].equals("[IGNORE]")) {
            next_line(null);
        } else if (separated[0].equals("[SAVE]")) {
            next_line(null);
        } else if (separated[0].equals("[NAMECHAR]")) {
            next_line(null);
        } else if (separated[0].equals("[CLEARNAMECHAR]")) {
            next_line(null);
        } else if (separated[0].equals("[CHOICESIMAGE]")) {
            next_line(null);
        } else if (separated[0].equals("[NPLAYER]")) {
            next_line(null);
        } else {
            if (line != null) {
                next_line(null);
            } else {
                finish();
            }
        }
    }

}


