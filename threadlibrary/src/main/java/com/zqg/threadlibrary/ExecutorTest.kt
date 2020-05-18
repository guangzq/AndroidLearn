package com.zqg.threadlibrary

import java.util.*
import java.util.concurrent.*

/**
 * <pre>
 *     author: zhuqiguang
 *     date  : 2020/5/11 11:01
 *     email : qiguang.zhu@foxmail.com
 *     desc  : TODO
 * </pre>
 */
fun main() {
    println("kotlin test == ")
//    ExecutorTest().single()
//    ExecutorTest().cached()
//    ExecutorTest().newFixedThreadPool()
//    ExecutorTest().newScheduledThreadPool()
//    ExecutorTest().moreThanCoreCount()
    ExecutorTest().nonCore()
//    ExecutorTest().rejectHandle()
}

class ExecutorTest {
    fun single() {
        val newSingleThreadExecutor = Executors.newSingleThreadExecutor()
        for (i in 0..5) {
            newSingleThreadExecutor.submit {
                println("线程 " + Thread.currentThread().name + "正在执行task: " + i)
            }
            Thread.sleep(1000)
        }
        newSingleThreadExecutor.shutdown()
    }

    fun cached() {
        val newCachedThreadPool = Executors.newCachedThreadPool()
        for (i in 1..5) {
            //原来只需要500ms执行完毕任务，
            // 但是休眠1s导致在新的任务提交前，线程“pool-1-thread-1”已经处于空闲状态
            Thread.sleep(1000)
            newCachedThreadPool.submit {
                println("线程 " + Thread.currentThread().name + "正在执行task: " + i)
                Thread.sleep(500)
            }
        }
        newCachedThreadPool.shutdown()
    }

    fun newFixedThreadPool() {
        //创建一个固定的可重用的线程池
        val newFixedThreadPool = Executors.newFixedThreadPool(2)
        for (i in 1..1000000) {
            //原来只需要500ms执行完毕任务，
            // 但是休眠1s导致在新的任务提交前，线程“pool-1-thread-1”已经处于空闲状态
            newFixedThreadPool.submit {
                println("线程 " + Thread.currentThread().name + "正在执行task: " + i)
                Thread.sleep(500)
            }
        }
        newFixedThreadPool.shutdown()
    }

    fun newScheduledThreadPool() {
        val newScheduledThreadPool = Executors.newScheduledThreadPool(2)
        newScheduledThreadPool.scheduleAtFixedRate({
            println("线程 " + Thread.currentThread().name + "报时 " + Date())
        }, 0, 1000,
                TimeUnit.MILLISECONDS)
        Thread.sleep(5000)
        newScheduledThreadPool.shutdown()
    }

    fun moreThanCoreCount() {
        //创建一个固定的可重用的线程池
        val newFixedThreadPool = Executors.newFixedThreadPool(3) as ThreadPoolExecutor
        for (i in 1..5) {
            //原来只需要500ms执行完毕任务，
            //但是休眠1s导致在新的任务提交前，线程“pool-1-thread-1”已经处于空闲状态
            newFixedThreadPool.execute {
                println("线程 " + Thread.currentThread().name + "正在执行task: " + i)
                Thread.sleep(4000)
            }
            println("此时等待队列中有 " + newFixedThreadPool.queue.size + " 个元素")
            //每500ms向线程池中提交任务
            Thread.sleep(500)
        }
        newFixedThreadPool.shutdown()
    }

    fun nonCore() {
        val threadPoolExecutor = ThreadPoolExecutor(2, 3,
                0, TimeUnit.MILLISECONDS, LinkedBlockingQueue(2))
        for (i in 1..20) {
            threadPoolExecutor.submit {
                println("线程：" + Thread.currentThread().name + " 正在执行的task " + i)
                Thread.sleep(5000)
            }
//            println("此时等待队列中有 " + threadPoolExecutor.queue.size + "个元素")
        }
        threadPoolExecutor.shutdown()
    }

    fun rejectHandle() {
        val threadPoolExecutor = ThreadPoolExecutor(2, 3,
                0, TimeUnit.MILLISECONDS, LinkedBlockingQueue(2))
        for (i in 1..6) {
            threadPoolExecutor.execute {
                println("线程：" + Thread.currentThread().name + " 正在执行的task " + i)
                Thread.sleep(5000)
            }
            println("此时等待队列中有 " + threadPoolExecutor.queue.size + "个元素")
        }
        threadPoolExecutor.shutdown()
    }
}