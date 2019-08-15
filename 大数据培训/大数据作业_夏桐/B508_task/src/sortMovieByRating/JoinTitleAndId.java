package sortMovieByRating;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import com.sun.swing.internal.plaf.metal.resources.metal;



public class JoinTitleAndId {


	public JoinTitleAndId(){}


	
    public static class JoinMapper extends Mapper<Object, Text, Text, Text> {
        private Text movieid = new Text();
        //private Text title = new Text();
        private Text titleOrRating = new Text();
        public JoinMapper() {}
     
        public void map(Object key, Text value,Context context) throws IOException, InterruptedException {
        	String itr = new String(value.toString());
            Pattern p = Pattern.compile(".*\\d+.*");
            Matcher m = p.matcher(itr);
        	if(!(itr.isEmpty())&&(m.matches())){	//判断当前行是否为空，是否为表头,包含数字则不为表头
        		String[] row = itr.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
    			//String[] tokens = value.toString().split("?=([^\"]*\"[^\"]*\")*[^\"]*$");
        		String id = new String(row[0]);
        		String val = new String(row[1].trim());
        		//System.out.print(val+"   ");
        		this.movieid.set(id);
        		this.titleOrRating.set(val);
        		context.write(this.movieid,this.titleOrRating); 
        	}
        }
    }
 
    public static class ValueToKeyReducer extends Reducer<Text, Text, Text, FloatWritable> {
        private FloatWritable ratings = new FloatWritable();
        private Text title = new Text();
        public ValueToKeyReducer() {}
        @Override
        public void reduce(Text key, Iterable<Text> values,Context context) throws IOException, InterruptedException {
        	 for (Text i : values){
                 Pattern digit = Pattern.compile("[0-9]+\\.[0-9]+");
                 Matcher find = digit.matcher(i.toString());
                 if(find.matches()){
                	 this.ratings.set(Float.parseFloat(i.toString().trim()));
                 }else{
                	 this.title.set(i.toString()+",");
                 }
        		//String xx = new String(i.get());
        	 }
    		 context.write(this.title,this.ratings);
        	
        }
    }

	/*
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String[] otherArgs = (new GenericOptionsParser(conf, args)).getRemainingArgs();
        if(otherArgs.length < 2) {
            System.err.println("Usage: sort user ratinng <in> [<in>...] <out>");
            System.exit(2);
        }
        
        Job jobJoin = Job.getInstance(conf, "Sort movie rating");
        jobJoin.setJarByClass(JoinTitleAndId.class);
        
        jobJoin.setMapperClass(JoinTitleAndId.JoinMapper.class);
        jobJoin.setReducerClass(JoinTitleAndId.ValueToKeyReducer.class);
        
        jobJoin.setMapOutputKeyClass(Text.class);
        jobJoin.setMapOutputValueClass(Text.class);
        
        jobJoin.setOutputKeyClass(Text.class);
        jobJoin.setOutputValueClass(FloatWritable.class);
        //定义input和output参数
        FileInputFormat.addInputPath(jobJoin, new Path(otherArgs[0]));
        FileInputFormat.addInputPath(jobJoin, new Path(otherArgs[1]));
        FileOutputFormat.setOutputPath(jobJoin, new Path(otherArgs[otherArgs.length - 1]));
        
        System.exit(jobJoin.waitForCompletion(true)?0:1);
    }
	*/

}
