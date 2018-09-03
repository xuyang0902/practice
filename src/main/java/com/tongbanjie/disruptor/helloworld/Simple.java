package com.tongbanjie.disruptor.helloworld;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.UUID;
import java.util.concurrent.Executors;

public class Simple {

    public static void main(String[] args) throws InterruptedException {

        int ringBufferSize = 2 << 0;
        Disruptor<ValueEvent> disruptor = new Disruptor<ValueEvent>(ValueEvent.EVENT_FACTORY, ringBufferSize,
                Executors.defaultThreadFactory(), ProducerType.SINGLE, new BlockingWaitStrategy());


        EventHandler<ValueEvent> eventHandler = new EventHandler<ValueEvent>() {

            @Override
            public void onEvent(final ValueEvent event, final long sequence, final boolean endOfBatch) throws Exception {
                System.out.println("Sequence: " + sequence);
                System.out.println("ValueEvent: " + event.getValue());
            }
        };

        disruptor.handleEventsWith(eventHandler);

        RingBuffer<ValueEvent> ringBuffer = disruptor.start();




        Thread.sleep(3000L);

        for (long i = 10; i < 11; i++) {
            String uuid = UUID.randomUUID().toString();
            // Two phase commit. Grab one of the 1024 slots
            long seq = ringBuffer.next();
            ValueEvent valueEvent = ringBuffer.get(seq);
            valueEvent.setValue(uuid);
            ringBuffer.publish(seq);
        }


        disruptor.shutdown();

    }
}