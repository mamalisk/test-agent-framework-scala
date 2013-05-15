
// Actually IDE specific settings belong into ~/build.sbt,
// but in order to ease the setup for the training we put it here.

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

EclipseKeys.eclipseOutput := Some(".target")

EclipseKeys.withSource := true
