/*
 * Copyright 2017 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package handlers

import com.google.inject.{Inject, Singleton}
import play.api.http.{DefaultHttpRequestHandler, HttpConfiguration, HttpErrorHandler, HttpFilters}
import play.api.mvc.{Handler, RequestHeader}
import uk.gov.hmrc.play.microservice.bootstrap.Routing.RemovingOfTrailingSlashes

@Singleton
class RequestHandler @Inject()(errorHandler: HttpErrorHandler, configuration: HttpConfiguration, filters: HttpFilters,
                               router: prod.Routes)
  extends DefaultHttpRequestHandler(router, errorHandler, configuration, filters) with RemovingOfTrailingSlashes {

  override def routeRequest(request: RequestHeader): Option[Handler] = {
    onRouteRequest(request) match {
      case None => super.routeRequest(request)
      case result => result
    }
  }
}
