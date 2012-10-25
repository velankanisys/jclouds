/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.ec2;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.jclouds.concurrent.Timeout;
import org.jclouds.ec2.features.TagApi;
import org.jclouds.ec2.features.WindowsApi;
import org.jclouds.ec2.services.AMIClient;
import org.jclouds.ec2.services.AvailabilityZoneAndRegionClient;
import org.jclouds.ec2.services.ElasticBlockStoreClient;
import org.jclouds.ec2.services.ElasticIPAddressClient;
import org.jclouds.ec2.services.InstanceClient;
import org.jclouds.ec2.services.KeyPairClient;
import org.jclouds.ec2.services.SecurityGroupClient;
import org.jclouds.ec2.services.WindowsClient;
import org.jclouds.javax.annotation.Nullable;
import org.jclouds.location.Region;
import org.jclouds.location.functions.RegionToEndpointOrProviderIfNull;
import org.jclouds.rest.annotations.Delegate;
import org.jclouds.rest.annotations.EndpointParam;

import com.google.common.annotations.Beta;
import com.google.inject.Provides;

/**
 * Provides synchronous access to EC2 services.
 * 
 * @author Adrian Cole
 */
@Timeout(duration = 180, timeUnit = TimeUnit.SECONDS)
public interface EC2Client {
   /**
    * 
    * @return the Region codes configured
    */
   @Provides
   @Region
   Set<String> getConfiguredRegions();
   
   /**
    * Provides synchronous access to AMI services.
    */
   @Delegate
   AMIClient getAMIServices();

   /**
    * Provides synchronous access to Elastic IP Address services.
    */
   @Delegate
   ElasticIPAddressClient getElasticIPAddressServices();

   /**
    * Provides synchronous access to Instance services.
    */
   @Delegate
   InstanceClient getInstanceServices();

   /**
    * Provides synchronous access to KeyPair services.
    */
   @Delegate
   KeyPairClient getKeyPairServices();

   /**
    * Provides synchronous access to SecurityGroup services.
    */
   @Delegate
   SecurityGroupClient getSecurityGroupServices();

   /**
    * Provides asynchronous access to Windows services.
    */
   @Delegate
   WindowsClient getWindowsServices();

   /**
    * Provides synchronous access to Availability Zones and Regions services.
    */
   @Delegate
   AvailabilityZoneAndRegionClient getAvailabilityZoneAndRegionServices();

   /**
    * Provides synchronous access to Elastic Block Store services.
    */
   @Delegate
   ElasticBlockStoreClient getElasticBlockStoreServices();
   
   /**
    * Provides synchronous access to Windows features.
    */
   @Delegate
   @Beta
   WindowsApi getWindowsApi();

   @Delegate
   @Beta
   WindowsApi getWindowsApiForRegion(
            @EndpointParam(parser = RegionToEndpointOrProviderIfNull.class) @Nullable String region);
   
   /**
    * Provides synchronous access to Tag features.
    */
   @Delegate
   @Beta
   TagApi getTagApi();

   @Delegate
   @Beta
   TagApi getTagApiForRegion(
            @EndpointParam(parser = RegionToEndpointOrProviderIfNull.class) @Nullable String region);

}
