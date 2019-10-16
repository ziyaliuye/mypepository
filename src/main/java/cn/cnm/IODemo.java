package cn.cnm;

/*
_ooOoo_
o8888888o
88" . "88
(| -_- |)
O\ = /O
___/`---'\____
 .   ' \\| |// `.
        / \\||| : |||// \
 / _||||| -:- |||||- \
 | | \\\ - /// | |
        | \_| ''\---/'' | |
 \ .-\__ `-` ___/-. /
___`. .' /--.--\ `. . __
        ."" '< `.___\_<|>_/___.' >'"".
        | | : `- \`.;`\ _ /`;.`/ - ` : | |
 \ \ `-. \_ __\ /__ _/ .-` / /
 ======`-.____`-.___\_____/___.-`____.-'======
 `=---='
 .............................................
佛曰：bug泛滥，我已瘫痪！
*/

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author lele
 * @version 1.0
 * @Description I/O是Input/Output的缩写， I/O技术是非常实用的技术， 用于处理设备之间的数据传输。 如读/写文件，网络通讯（服务器与客户端之间数据传输也是IO）等
 * java.io 包下提供了各种“流”类和接口，用以获取不同种类的数据，并通过标准的方法输入或输出数据
 * 输入和输出是针对程序（内存， 程序运行在内存中）来说的
 * <p>
 * 流的角色：
 * 字节流：直接从数据源/目的地读取数据
 * 处理流：不直接连接数据源/目的地， 而是“连接”在已存在的流（节点流或处理流）之上， 通过对数据的处理为程序提供更为强大的读写功能
 * @Email 414955507@qq.com
 * @date 2019/10/14 22:55
 */
public class IODemo {
    /*
     *  IO涉及40多个类， 都是从4个抽象基类派生出来的：
     *   输入流：InputStream（字节流）/ Reader（字符流）
     *   输出流：OutputStream（字节流）/ Write（字符流）
     *  派生的子类可以根据名称的"后半段"来区分是字节流（InputStream/OutputStream）还是字符流（Reader/Write）
     *
     *  抽象基类            节点流（或文件流）       缓冲流（处理流的一种）
     *  InputStream     FileInputStream         BufferedInputStream
     *  OutputStream    FileOutputStream        BufferedOutputStream
     *  Reader          FileReader              BufferedReader
     *  Writer          FileWriter              BufferedWriter
     */
    public static void main(String[] args) {
        /* 字符流只能处理文本形式的文件， 不能处理图片、视频等文件 */
        // 文本文件读取操作
        reader();
        // 文本文件写入操作
        write();
        // 文本文件读取和写入（复制文件）
        copy();
        /* 字节流可以复制任何形式的文件, 但是如果涉及处理字符文件会比较麻烦， 一个中文需要几个字节来存储 */
        copyFile();
        // 带缓冲区的实现对图片等文件的复制
        bufferCopyFile();
        // 带缓冲区的文本文件的复制
        bufferCopy();
        // 转换流的使用
        changeStream();
    }

    private static void reader() {
        /* 读取示例一 */
        // File类初始化， 指定好对应的文件
        File file = new File("D:\\11.txt");
        // FileReader流的初始化
        FileReader fileReader = null;
        /* 涉及流处理的资源最好手动try-catch， 不要将异常抛出， 否则可能会出现内存泄露问题 */
        try {
            // 准备将文件的内容输入（Input）到内存中， 文件不存在则会抛异常
            fileReader = new FileReader(file);
            // 开始读取, read()返回读取第一个字符， 如果达到文件末尾， 则返回-1
            //        int data = fileReader.read();
            //        // 如果读取为-1则终止循环， 说明文件已经读取完毕
            //        while (data != -1) {
            //            // 由于读取的是字符对应的ASCII码， 所以需进转换
            //            System.out.print((char) data);
            //            // 读完一个字符就继续往下读取
            //            data = fileReader.read();
            //        }
            /* 这种写法和注释的等价， 看起来简约一些而已 */
            int data;
            while ((data = fileReader.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("系统找不到指定文件...");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 不管是输入还是输出流， 操作完后一定要手动关闭资源
            // 并且必须先判断对象是否为空， 否则IOException是抓不到NullPointException的
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("\n==================================");

        /* 读取示例二, 在示例一的基础上升级， 因为每次读取一个字符就处理效率太低, 使用一个数组进行缓冲 */
        // 1：File类初始化， 指定好对应的文件
        File file1 = new File("D:\\11.txt");
        // 2：FileReader流的初始化
        FileReader fileReader1 = null;
        try {
            fileReader1 = new FileReader(file1);
        } catch (FileNotFoundException e) {
            System.out.println("系统找不到指定文件...");
        }
        // 3：读入的操作
        // 先创建一个字符数组， 用于接收一定量的字符数据
        char[] chars = new char[5];
        try {
            // 将数组传入FileReader的构造器， 之后返回每次读取的字符个数， 如果到文件末尾则返回-1
            int len;
            /*
             * 断言：assert[boolean 表达式]
             *   如果[boolean表达式]为true，则程序继续执行
             *   如果为false，则程序抛出AssertionError，并终止执行
             * assert[boolean 表达式 : 错误表达式 （日志）]
             *   如果[boolean表达式]为true，则程序继续执行
             *   如果为false，则程序抛出java.lang.AssertionError，输出[错误信息]
             */
            assert fileReader1 != null;
            while ((len = fileReader1.read(chars)) != -1) {
                /*
                 * 错误写法一：每次读进来几个就取几个， 不能直接取数组长度， 这个数组相当于一个载体：
                 *  假设数组长度为5， 最后一次到文件末尾了只取了3个字符， FileReader只会将数组的前3个索引位置的内容替换
                 *  而最后2个字符仍然是上一轮的内容， 那么就会导致读取的数据多了最后两个字符
                 */
//                for (int i = 0; i < chars.length; i++) {
//                    System.out.print(chars[i]);
//                }

                // 错误写法二：错误的原因和错位一一致, 最后一次输出会有问题
//                String str = new String(chars);
//                System.out.print(str);

                /* 正确写法 */
//                for (int i = 0; i < len; i++) {
//                    System.out.print(chars[i]);
//                }
                // 初始化字符串时， 每次从数组中取指定长度
                String str = new String(chars, 0, len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader1 != null) {
                try {
                    // 4：资源关闭
                    fileReader1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void write() {
        /* 写入示例一 */
        // 1：定义File类， 这时的File类表示写出到的文件
        File file = new File("D:\\2.txt");
        FileWriter fileWriter = null;
        // 2：提供FileWriter对象， 用于数据的写出
        // 无参构造器：当File对应的文件存在时则覆盖， 当File对应的文件不存在则创建
        // 有参构造器：第二个参数为boolean类型， false表示替换已存在文件， true表示追加到现有文件的末尾
        try {
            fileWriter = new FileWriter(file, true);
        } catch (IOException e) {
            System.out.println("系统定义写出文件出错...");
            e.printStackTrace();
        }
        // 3：写出的具体操作
        try {
            assert fileWriter != null;
            fileWriter.write("you are sb....\n");
            fileWriter.write("you are sb....\n");
            fileWriter.write("you are big sb....\n");
        } catch (IOException e) {
            System.out.println("写入失败");
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    // 4：流资源的关闭
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void copy() {
        // 1：创建File类的对象， 指明读取和写入的文件
        File srcFile = new File("D:\\11.txt");
        File descFile = new File("D:\\3.txt");
        // 2：创建输入流和输出流
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader(srcFile);
            fileWriter = new FileWriter(descFile);

            // 3：数据的读入和写出操作
            char[] chars = new char[5];
            int len;    // 记录每次读取到数据中的字符个数
            while ((len = fileReader.read(chars)) != -1) {
                /* 写出数据， 每次写入数组的指定索引范围的数据（防止最后一次写入异常） */
                fileWriter.write(chars, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4：关闭流资源
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 实现对图片等文件的复制
    private static void copyFile() {
        // 1：创建File对象
        File srcFile = new File("D:\\1.jpg");
        File destFile = new File("D:\\2.jpg");

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            // 2：创建流对象
            fileInputStream = new FileInputStream(srcFile);
            fileOutputStream = new FileOutputStream(destFile);
            // 3：读取数据操作
            // 创建一个字节数组， 用于存储读取的字节
            byte[] bytes = new byte[1024];
            // 每次读取字节的长度
            int len;
            while ((len = fileInputStream.read(bytes)) != -1) {
                // 将数据写入文件
                fileOutputStream.write(bytes, 0, len);
            }
            System.out.println("文件复制成功....");
        } catch (FileNotFoundException e) {
            System.out.println("系统找不到指定文件...");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4：关闭资源
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //
    /*
     * 带缓冲区的实现对图片等文件的复制， 写法都差不多， 一个“处理流”套接在已有的“节点流”上， 然后使用"处理流"做操作
     * BufferedInputStream、BufferedOutputStream 分别用于包裹 FileInputStream、FileOutputStream
     * BufferedReader、BufferedWriter 分别用于包括 Reader、Writer
     */
    private static void bufferCopyFile() {
        // 1：创建File对象
        File srcFile = new File("D:\\1.jpg");
        File destFile = new File("D:\\2.jpg");

        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            // 2：创建流对象
            fileInputStream = new FileInputStream(srcFile);
            fileOutputStream = new FileOutputStream(destFile);
            /*
             *  处理流：不直接连接数据源/目的地， 而是“连接”在已存在的流（节点流或处理流）之上， 通过对数据的处理为程序提供更为强大的读写功能
             *  缓冲流是处理流的一种
             */
            // 创建缓冲输入/输出字节流， 包裹“处理流”对象
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            // 3：读取数据操作
            // 创建一个字节数组， 用于存储读取的字节
            byte[] bytes = new byte[1024];
            // 每次读取字节的长度
            int len;
            /* 使用带缓冲区的流进入读取和写入操作，只是在处理流的基础上做了加工， 实际底层还是用处理流做操作 */
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                // 将数据写入文件
                bufferedOutputStream.write(bytes, 0, len);
                /* 手动刷新缓冲区， 一般不需要手动刷新 */
                bufferedOutputStream.flush();
            }
            System.out.println("文件复制成功....");
        } catch (FileNotFoundException e) {
            System.out.println("系统找不到指定文件...");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /* 4：关闭顺序：先关闭外层的流（处理流）， 再关闭内层的流（节点流）,但是外层的流在关闭是会自动关闭内层的流， 所以可以省略 */
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* 带缓冲区的文本文件读取和写入， 简写版 */
    private static void bufferCopy() {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File("D:\\1.txt")));
            bufferedWriter = new BufferedWriter(new FileWriter(new File("D:\\2.txt")));
            /* 方式一： */
//            char[] chars = new char[1024];
//            int len;
//            while ((len = bufferedReader.read(chars)) != -1) {
//                bufferedWriter.write(chars, 0, len);
//                /* 手动刷新缓冲区， 一般情况下不需要手动刷新 */
//                bufferedWriter.flush();
//            }
            /* 方式二， 使用BufferedReader独有的readLind()方法每次读取一行文本， 直到返回null为止 */
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                /* readLine()方法只是每次读取一行， 但是不会读取换行符 */
                bufferedWriter.write(data);
                // 创建一个空白的行， 相当于换行符, 反正最后文本会多一个换行符
                bufferedWriter.newLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("系统找不到指定文件....");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /* 4：关闭顺序：先关闭外层的流（处理流）， 再关闭内层的流（节点流）,但是外层的流在关闭是会自动关闭内层的流， 所以可以省略 */
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
     * 转换流：是处理流的一种， 有InputSreamReader和OutputSreamWriter两种（属于字符流）
     * InputStreamReader：将输入的字节流变成字符流（解码）
     * OutputStreamWriter：将输出的字符流变成字节流,字符流的输入对象变成字节流输入对象（编码）
     * 解码：字节、字节数组 =》字符数组、字符串（字节输入=》字符输入）
     * 编码：字符数组、字符串 =》字节、字节数组（字符输出=》字节输出）
     */
    private static void changeStream() {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        try {
            /* FileInputStream构造器可以直接传文件路径， 省略new File */
            fileInputStream = new FileInputStream("D:\\1.txt");
            fileOutputStream = new FileOutputStream("D:\\2.txt");
            // 创建一个字节流转字符流对象, 包裹字节输入流， 第二个参数不写默认按系统编码格式走
            // 具体使用哪个字符集， 取决于文本文件本身的编码格式
            inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "GBK");
            char[] chars = new char[20];
            int len;
            while ((len = inputStreamReader.read(chars)) != -1) {
                String str = new String(chars, 0, len);
                System.out.println(str);
                /* 写入到文件中 */
                outputStreamWriter.write(str);
            }
        } catch (FileNotFoundException e) {
            System.out.println("系统找不到指定文件....");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /* 关闭处理流会自动关闭它所包裹的字节流 */
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
