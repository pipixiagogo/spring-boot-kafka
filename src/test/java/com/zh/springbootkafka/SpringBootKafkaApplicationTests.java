package com.zh.springbootkafka;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootKafkaApplicationTests {

	@Test
	public void contextLoads() {
		byte[] bytes1=new byte[]{(byte)0x01,(byte)0x01,(byte)0x02,(byte)0x02,(byte)0x02,(byte)0x02,(byte)0x03,(byte)0x03,(byte)0x03,(byte)0x03};
		ByteBuf buffer = Unpooled.buffer();
		buffer.writeBytes(bytes1);
		byte b = buffer.readByte();
		System.out.println(b);

		byte b1 = buffer.readByte();
		System.out.println(b1);

		long l = buffer.readUnsignedInt();
		System.out.println(l);


//		byte[]bTime=new byte[4];
//		System.out.println();
//		buffer.readBytes(bTime);
//
//		for(byte b2:bTime){
//			System.out.print(b2);
//		}
//		System.out.println();
//
//		buffer.readBytes(bTime);
//		for(byte b2:bTime){
//			System.out.print(b2);
//		}
	}

}
