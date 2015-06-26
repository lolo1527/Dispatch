'use strict';

angular.module('dispatch.version', [
  'dispatch.version.interpolate-filter',
  'dispatch.version.version-directive'
])

.value('version', '0.1');
