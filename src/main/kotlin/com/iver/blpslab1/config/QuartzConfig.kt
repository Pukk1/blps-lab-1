package com.iver.blpslab1.config

import org.quartz.JobDetail
import org.quartz.SimpleTrigger
import org.quartz.Trigger
import org.quartz.spi.TriggerFiredBundle
import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.quartz.JobDetailFactoryBean
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean
import org.springframework.scheduling.quartz.SpringBeanJobFactory

@Configuration
class QuartzConfig {

    @Bean
    fun jobDetail(): JobDetailFactoryBean {
        val jobDetailFactory = JobDetailFactoryBean()
        jobDetailFactory.setJobClass(AsyncScheduling::class.java)
        jobDetailFactory.setDescription("Invoke Sample Job service...")
        jobDetailFactory.setDurability(true)
        return jobDetailFactory
    }

    @Bean
    fun trigger(job: JobDetail): SimpleTriggerFactoryBean {
        val trigger = SimpleTriggerFactoryBean()
        trigger.setJobDetail(job)
        trigger.setRepeatInterval(10000)
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY)
        return trigger
    }

    @Bean
    fun scheduler(trigger: Trigger, job: JobDetail, springBeanJobFactory: SpringBeanJobFactory): SchedulerFactoryBean {
        val schedulerFactory = SchedulerFactoryBean()
        schedulerFactory.setJobFactory(springBeanJobFactory)
        schedulerFactory.setJobDetails(job)
        schedulerFactory.setTriggers(trigger)
        return schedulerFactory
    }

    @Bean
    fun springBeanJobFactory(applicationContext: ApplicationContext): SpringBeanJobFactory {
        val jobFactory = AutowiringSpringBeanJobFactory()
        jobFactory.setApplicationContext(applicationContext)
        return jobFactory
    }

    class AutowiringSpringBeanJobFactory : SpringBeanJobFactory(), ApplicationContextAware {
        @Transient
        private var beanFactory: AutowireCapableBeanFactory? = null
        override fun setApplicationContext(context: ApplicationContext) {
            beanFactory = context.autowireCapableBeanFactory
        }

        @Throws(Exception::class)
        override fun createJobInstance(bundle: TriggerFiredBundle): Any {
            val job = super.createJobInstance(bundle)
            beanFactory!!.autowireBean(job)
            return job
        }
    }
}
