import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;

public class q4 {

    public static class MyMapper1 extends Mapper<LongWritable, Text, Text, Text> {
        private static final IntWritable one = new IntWritable(1);
        private Text movie = new Text();
        private Text actor = new Text();
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] tokens = value.toString().trim().split(",");
            try {
                if (tokens.length == 4) {
                    movie.set(tokens[2]);
                    actor.set(tokens[0]);
                }
            } catch (NumberFormatException npe) {
                System.err.println("Non numeric Node ; ignoring it");
                npe.getCause();
                return;
            }
            if (!actor.equals(null) && (!actor.equals(null))) {
                context.write(movie, actor);
            }
        }
    }

    public static class MyReducer1 extends Reducer<Text, Text, Text, Text> {
        protected void reduce(Text key, Iterable<Text> values,
                              Context context) throws IOException, InterruptedException {
            StringBuilder nNodes = new StringBuilder();
            if (key.equals(""))
                nNodes.append("(0),");
            else
                nNodes.append(" = ");
            for (Text n : values) {
                nNodes.append(n.toString()).append(",");
            }
            context.write(key, new Text(nNodes.toString()));
        }
    }
    
    public static class MyMapper2 extends Mapper<LongWritable, Text, Text, Text> {

        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] cols = value.toString().trim().split("=");
            String[] actrs = cols[1].trim().split(",");
            List<String> lst = Arrays.asList(actrs);

            if(lst.contains("Audrey Gelfund") && lst.contains("John Malkovich"))
                context.write(new Text(cols[0] + ":"), new Text("Audrey Gelfund -> John Malkovich"));

            if(lst.contains("John Malkovich") && lst.contains("Kevin Bacon (I)"))
                context.write(new Text(cols[0] + ":"), new Text("John Malkovich -> Kevin Bacon"));
            }
        }

    public static void main(String[] arg0) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "job1");
        job.setJarByClass(q4.class);
        job.setMapperClass(MyMapper1.class);
        job.setReducerClass(MyReducer1.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(arg0[0]));
        FileOutputFormat.setOutputPath(job, new Path(arg0[1]+"temp"));
        job.waitForCompletion(true);

        Configuration conf2 = new Configuration();
        Job job2 = Job.getInstance(conf2, "job2");
        job2.setJarByClass(q4.class);
        job2.setMapperClass(MyMapper2.class);
        job2.setMapOutputKeyClass(Text.class);
        job2.setMapOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job2, new Path(arg0[1]+"temp"));
        FileOutputFormat.setOutputPath(job2, new Path(arg0[1]));
        System.exit(job2.waitForCompletion(true) ? 0 : 1);
    }

}

