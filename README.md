dropwizard-hystrix
==================

This is an example of how to use Hystrix in a Dropwizard application. 

It is the same scenario as defined in my [Hystrix/Tenacity/Breakerbox example](https://github.com/chbatey/integration-points-example) so please read that first!!

See my blog post on how this app differs from the tenacity example: [Dropwizard + Hystrix](http://christopher-batey.blogspot.co.uk/2014/08/using-hystrix-with-dropwizard.html)

This example is configured to push metrics to graphite on port 192.168.10.120, to launch a vagrant VM with graphite on this port then checkout this project [graphite-vm](https://github.com/chbatey/graphite-vm) and run vagrant up.
