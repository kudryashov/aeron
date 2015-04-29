/*
 * Copyright 2014 - 2015 Real Logic Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.real_logic.aeron.driver;

import java.net.InetSocketAddress;

/**
 * Uniform random loss generator
 */
public class PercentageLossGenerator implements LossGenerator
{
    //private final Random random;
    private double lossRate;
    private double messageCount;

    /**
     * Construct loss generator with given loss rate as percentage and random seed
     *
     * @param lossRate for generating loss
     * @param lossSeed for random seeding
     */
    public PercentageLossGenerator(final double lossRate) throws Exception
    {
        if (lossRate <= 0 || lossRate > 100)
        {
            throw new Exception("Loss rate is out of range " + lossRate);
        }
        this.lossRate = lossRate;
        this.messageCount = 0;
    }

    public void setPercentageLossRate(final double lossRate) throws Exception
    {
        if (lossRate <= 0 || lossRate > 100)
        {
            throw new Exception("Loss rate is out of range " + lossRate);
        }
        this.lossRate = lossRate;
    }

    /** {@inheritDoc} */
    @Override
    public boolean shouldDropFrame(final InetSocketAddress address, final int length)
    {
        messageCount++;
        return messageCount % (100 / lossRate) == 0 ? true : false;
    }
}
